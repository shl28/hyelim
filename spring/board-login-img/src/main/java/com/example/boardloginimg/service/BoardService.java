package com.example.boardloginimg.service;

import com.example.boardloginimg.domain.Board;
import com.example.boardloginimg.domain.BoardImage;
import com.example.boardloginimg.domain.User;
import com.example.boardloginimg.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import net.coobird.thumbnailator.Thumbnails;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class BoardService {
    private final BoardRepository boardRepository;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @PostConstruct // 사전 점검 한번 실행 - 폴더 없으면 폴더 만들기
    void ensureUploadDir() {
        File dir = new File(normalizeDir(uploadDir));
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("업로드 폴더를 만들 수 없습니다: " + uploadDir);
        }
    }
    // mkdirs - 부모 폴더 없으면 부모 폴더부터 줄줄이 다 만듦 C:/data/upload

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board create(String title, String content, User author, List<MultipartFile> files) {
        Board board = new Board(title, content, author, LocalDateTime.now());
        boardRepository.save(board);
        storeImages(board, files);
        return boardRepository.findByIdWithAuthorAndImages(board.getId()).orElse(board);
    }

    private void storeImages(Board board, List<MultipartFile> files) {
        if (files == null || files.isEmpty()) return;

        String dir = normalizeDir(uploadDir);

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            try {
                String originalName = file.getOriginalFilename();
                if (originalName == null || originalName.isBlank()) {
                    originalName = "file";
                }
                String savedName = UUID.randomUUID() + "_" + originalName;
                File saveFile = new File(dir + savedName);
                file.transferTo(saveFile); // 임시 저장소에 저장 -> 실제 하드디스크에 복사

                File thumbFile = new File(dir + "s_" + savedName);
                Thumbnails.of(saveFile).size(200, 200).toFile(thumbFile);
                // 썸네일 생성 크기 : 200 X 200

                BoardImage image = new BoardImage(originalName, savedName, dir, board);
                board.getImages().add(image);
            } catch (IOException e) {
                throw new RuntimeException("이미지 업로드 실패", e);
            }
        }
        boardRepository.save(board);
    }

    private String normalizeDir(String dir) {
        if (dir == null || dir.isBlank()) return "";

        return dir.endsWith("/") || dir.endsWith("\\") ? dir : dir + File.separator;
    }
//    window '/' 리눅스 '\\' 경로로 끝나면
//    File.separator 구분자 없다면 만들어줌 - 유효성검사 후 알아서 붙여줌
//    예: 디렉토리 C:/upload 업로드 이미지 test.jpg 둘이 합치면 C:/uploadtest.jpg
//    C:/upload/test.jpg

    public @Nullable List<Board> findAll() {
        return boardRepository.findAllWithAuthor();
    }

    public Board findById(Long id) {
        return boardRepository.findByIdWithAuthorAndImages(id).orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
    }
    // findAll전체를 조회하고
    // WithAuthor Board 엔티티 안에 WithAuthor 필드 변수 있는지 조회

    // Board 조회 (author는 안가지고 옴)
    // 트랜잭션 끝(세션종료)
    // 타임리프 p.author.name 접근
    // db 다시 조회하려고 시도
    // 세션이 없고 터짐

    @Transactional
    public void update(Long id,
                       String title,
                       String content,
                       String currentUsername,
                       List<MultipartFile> files,
                       List<Long> deleteImageIds) {
        Board board = boardRepository.findByIdWithAuthorAndImages(id)
                .orElseThrow(()-> new IllegalArgumentException("글이 없습니다."));

        if (!board.getAuthor().getUsername().equals(currentUsername))
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");

        // 1. 제목/내용 수정
        board.update(title, content);

        // 2. 선택된 이미지 삭제
        deleteSelectedImages(board, deleteImageIds);

        // 3. 새 이미지 추가
        storeImages(board, files);
    }

    private void deleteSelectedImages(Board board, List<Long> deleteImageIds) {
        if (deleteImageIds == null || deleteImageIds.isEmpty()) return;

        // 삭제할 이미지들 찾기
        List<BoardImage> toDelete = board.getImages().stream()
                .filter(img -> deleteImageIds.contains(img.getId()))
                .toList();

        // 물리 파일 + 메모리에서 제거
        for (BoardImage img : toDelete) {
            deletePhysicalFile(img.getSavedName());
            deletePhysicalFile(img.getThumbnailSavedName());
            board.getImages().remove(img);
        }
    }

    @Transactional
    public void delete(Long id, String username) {
        Board board = boardRepository.findByIdWithAuthorAndImages(id).orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (!board.getAuthor().getUsername().equals(username)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        for (BoardImage image : board.getImages()) {
            deletePhysicalFile(image.getSavedName());
            deletePhysicalFile(image.getThumbnailSavedName());
        }

        boardRepository.delete(board);
    }

    // 강사님 버전
//    public void delete(Long id, String currentUsername) {
//        Board board = boardRepository.findByIdWithAuthorAndImages(id).orElseThrow(()->new IllegalArgumentException("글이 없습니다."));
//
//        if (!board.getAuthor().getUsername().equals(currentUsername)) throw new IllegalArgumentException("작성자만 삭제 가능합니다.");
//
//        deleteImageFiles(board);
//        boardRepository.delete(board);
//    }
//
//    // img.getFilePath() : DB에 저장경로가 있으면 사용하고 없으면 기본 업로드 경로 사용
//    private void deleteImageFiles(Board board) {
//        for (BoardImage img : board.getImages()) { // 게시글에 달린 이미지 전부 순회
//            String base = img.getFilePath() != null ? img.getFilePath() : normalizeDir(uploadDir);
//            File f = new File(base + img.getSavedName());
//            File t = new File(base + img.getThumbnailSavedName());
//
//            if (f.exists()) f.delete();
//            if (t.exists()) t.delete();
//        }
//    }

    private void deletePhysicalFile(String filename) {
        try {
            String dir = normalizeDir(uploadDir);
            Path path = Paths.get(dir + filename);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}

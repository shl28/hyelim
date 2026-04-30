package com.example.boardlogin.service;

import com.example.boardlogin.domain.Board;
import com.example.boardlogin.domain.User;
import com.example.boardlogin.repository.BoardRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board create(String title, String content, User author) {
        Board board = new Board(title, content, author, LocalDateTime.now());
        return boardRepository.save(board);
    }

    public @Nullable List<Board> findAll() {
        return boardRepository.findAllWithAuthor();
    }

    public Board findById(Long id) {
        return boardRepository.findByIdWithAuthor(id).orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
    }
    // findAll전체를 조회하고
    // WithAuthor Board 엔티티 안에 WithAuthor 필드 변수 있는지 조회

    // Board 조회 (author는 안가지고 옴)
    // 트랜잭션 끝(세션종료)
    // 타임리프 p.author.name 접근
    // db 다시 조회하려고 시도
    // 세션이 없고 터짐

    @Transactional
    public void update(Long id, String title, String content, String currentUsername) {
        Board board = boardRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
        if (!board.getAuthor().getUsername().equals(currentUsername)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        board.update(title, content);
    }

    @Transactional
    public void delete(Long id, String currentUsername) {
        Board board = boardRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 없습니다."));
        if (!board.getAuthor().getUsername().equals(currentUsername)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        boardRepository.delete(board);
    }
}

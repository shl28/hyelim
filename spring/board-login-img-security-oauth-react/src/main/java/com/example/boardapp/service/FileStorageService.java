package com.example.boardapp.service;

import com.example.boardapp.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png");

    public record StoredFile(String originalName, String storedName, String thumbnailName, String dateFolder) {}

    public StoredFile store(MultipartFile file) {
        validate(file);

        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        String uuid = UUID.randomUUID().toString();
        String storedName = uuid + "." + extension;
        String thumbnailName = "thumb_" + storedName;

        String dateFolder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Path folderPath = Paths.get(uploadDir, dateFolder);

        try {
            Files.createDirectories(folderPath);

            Path targetPath = folderPath.resolve(storedName);
            file.transferTo(targetPath.toFile());

            Path thumbnailPath = folderPath.resolve(thumbnailName);
            Thumbnails.of(targetPath.toFile())
                    .size(200, 200)
                    .toFile(thumbnailPath.toFile());

            log.info("파일 저장 완료: {}", targetPath);

            return new StoredFile(originalName, storedName, thumbnailName, dateFolder);

        } catch (IOException e) {
            log.error("파일 저장 실패", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }

    public List<StoredFile> storeAll(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return List.of();
        }
        if (files.size() > 3) {
            throw new BadRequestException("이미지는 최대 3장까지 업로드 가능합니다.");
        }
        return files.stream().map(this::store).toList();
    }

    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("빈 파일은 업로드할 수 없습니다.");
        }

        // 용량 체크는 Spring이 application.yml에서 처리해주지만, 추가 안전망
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new BadRequestException("파일 크기는 10MB 이하여야 합니다.");
        }

        String originalName = file.getOriginalFilename();
        if (originalName == null || !originalName.contains(".")) {
            throw new BadRequestException("올바르지 않은 파일명입니다.");
        }

        String extension = getExtension(originalName);
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new BadRequestException("허용된 확장자: jpg, jpeg, png (입력: " + extension + ")");
        }
    }

    private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        return filename.substring(dotIndex + 1).toLowerCase();
    }
}
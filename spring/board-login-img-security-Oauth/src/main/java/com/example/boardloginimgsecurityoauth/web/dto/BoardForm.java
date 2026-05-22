package com.example.boardloginimgsecurityoauth.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class BoardForm {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 10000)
    private String content;

    private List<MultipartFile> files;

    private List<Long> deleteImageIds = new ArrayList<>();

    public void setDeleteImageIds(List<Long> deleteImageIds) {
        this.deleteImageIds = deleteImageIds;
    }

    public List<Long> getDeleteImageIds() {
        return deleteImageIds;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

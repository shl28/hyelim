package com.example.boardapp.dto;

import com.example.boardapp.domain.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostImageResponse {

    private Long id;
    private String originalName;
    private String url;
    private String thumbnailUrl;

    public static PostImageResponse from(PostImage image) {
        String baseUrl = "/uploads/" + image.getDateFolder() + "/";
        return new PostImageResponse(
                image.getId(),
                image.getOriginalName(),
                baseUrl + image.getStoredName(),
                baseUrl + image.getThumbnailName()
        );
    }
}

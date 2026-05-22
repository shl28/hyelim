package com.example.boardapp.dto;

import com.example.boardapp.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String memberNickname;
    private List<PostImageResponse> images;

    public static PostResponse from(Post post) {
        List<PostImageResponse> imageResponses = post.getImages().stream()
                .map(PostImageResponse::from)
                .toList();

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getMember().getNickname(),
                imageResponses
        );
    }
}

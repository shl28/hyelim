package com.example.secondproject.dto;

import com.example.secondproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        return Article.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}

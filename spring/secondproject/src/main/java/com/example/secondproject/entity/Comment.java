package com.example.secondproject.entity;

import com.example.secondproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if (dto.getId() != null) throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

        if (dto.getArticleId() != article.getId()) throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

        return Comment.builder()
                .id(dto.getId())
                .article(article)
                .nickname(dto.getNickname())
                .body(dto.getBody())
                .build();
    }

    public void patch(CommentDto dto) {
        if (this.id != dto.getId()) throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 입니다.");

        if (dto.getNickname() != null) this.nickname = dto.getNickname();
        if (dto.getBody() != null) this.body = dto.getBody();
    }
}

package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
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

    @ManyToOne  // 일대다 관계 (게시글 1개, 댓글 여러개)
    @JoinColumn(name = "article_id")  // article_id라는 이름의 외래키 컬럼 생성
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if (dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        }

        // 엔티티 생성 및 반환
//        return new Comment(
//                dto.getId(),
//                article,
//                dto.getNickname(),
//                dto.getBody()
//        );

        return Comment.builder()
                .id(dto.getId())
                .article(article)
                .nickname(dto.getNickname())
                .body(dto.getBody())
                .build();
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 입니다.");
        }

        // 객체 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        if (dto.getBody() != null)
            this.body = dto.getBody();


    }
}


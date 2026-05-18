package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

// 댓글 엔티티(인테리어 / 커뮤니티 공용) - 같은 테이블로 인테리어 / 커뮤니티 댓글 저장
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostType postType;

    @Column(nullable = false)
    private Long postId;

    // @ManyToOne InteriorPost 처럼 FK로 글엔티티를 직접 연결하지 않음
    // "어떤 종류의 글 + 그글 id" 찾음
    // 장점 : 댓글 테이블 CommentRepository 한벌로 쓸 수 있음
    // 단점 : JPA가 글과 자돋 연관을 보장하지 않음 PostType/postId를 서비스에 정확하게 넣어야 함

//    var comment = com.example.roomfit.domain.Comment.builder()
//            .postType(PostType.INTERIOR)
//            .postId(postId)
//       return commentRepository.findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc(
//    PostType.INTERIOR, postId, PostStatus.VISIBLE);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member author; // 댓글 작성자 , author_id = FK

    @ManyToOne(fetch = FetchType.LAZY) // 대댓글 - 같은 테이블을 자기 자신에게 연결
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Column(nullable = false, length = 1000)
    private String content;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.VISIBLE;
}

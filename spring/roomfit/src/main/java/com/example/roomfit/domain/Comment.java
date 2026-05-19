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
//댓글 엔티티 (인테리어 및 커뮤니티 공용)
//인테리어 글 , 커뮤니티 글 둘 다 같은 테이블로 댓글 저장하는 구조
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //postType + postId

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostType postType; // INTERIOR, COMMUNITY

    @Column(nullable = false)
    private Long postId; //해당 게시글 id(숫자만 저장 )

    //@ManyToOne InteriorPost 처럼 fk로 글엔티티를 직접 연결하지 안혹
    // "어떤종류의글 + 그글 id " 찾습니다.
    //장점 : 댓글 테이블 CommentRepository 한 벌로 쓸수 있음
    //단점 : jpa가 글과 자동 연관을 보자하지 않음 postType/postId를 서비스에 맞게 넣어야함

//    var comment = com.example.roomfit.domain.Comment.builder()
//            .postType(PostType.INTERIOR)
//            .postId(postId)

//    	return commentRepository.findByPostTypeAndPostIdAndStatusOrderByCreatedAtAsc(
//    PostType.INTERIOR, postId, PostStatus.VISIBLE);


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member author; //댓글 작성자 , author_id FK

    @ManyToOne(fetch = FetchType.LAZY) //대댓글 - 같은 데이블을 자기 자신에게 연결
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


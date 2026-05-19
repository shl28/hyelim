package com.example.roomfit.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
//인테리어 글 좋아요를 저장하는 post_likes
//@UniqueConstraint(columnNames = {"member_id", "post_id"}))
//member_id - 좋아요를 누른 회원 FK  post_id - 인테리어글 FK
// 같은 회원이 같은글에 좋아요를 두 번 넣지 못하게

@Table(name = "post_likes", uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "post_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //N개의 좋아요 -> 한명의 회원
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) //N개의 좋아요 -> 1개의 인테리어 글
    @JoinColumn(nullable = false)
    private InteriorPost post;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
// 한 사람이 여러글에 좋아요 가능
// 한 글에 여러 사람이 좋야요 가능
//(member_id + post_id) 조합은 테이블에 1번만
//public boolean toggleLike(Long postId, Long memberId) {
//    var existing = postLikeRepository.findByMemberIdAndPostId(memberId, postId);
//    if (existing.isPresent()) {
//        postLikeRepository.delete(existing.get());  // 좋아요 취소
//        post.setLikeCount(... - 1);
//        return false;
//    }
//    postLikeRepository.save(PostLike.builder().member(member).post(post).build());
//    post.setLikeCount(... + 1);
//    return true;
//}
//처음 좋아요 PostLike insert
// 다시 클릭 해당행 delete(토글)

package com.example.roomfit.repository;

import com.example.roomfit.domain.InteriorPost;
import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InteriorPostRepository extends JpaRepository<InteriorPost , Long> {
    //jpa에서 연관 엔티를 한 번에 조회(fetch join처럼 동작) - 조회할 때 이미지도 같이 가져와라
    @EntityGraph(attributePaths = "images")
    Page<InteriorPost> findByStatus(PostStatus status, Pageable pageable);
    //게시글상태 - 노출상태인 글 페이징 목록 - 이미지도 함께 로드 -> getThumbnailpath();

    @EntityGraph(attributePaths = "images")
    Page<InteriorPost> findByStatusAndStyle(PostStatus status, InteriorStyle style, Pageable pageable);
    //게시글상태 + 인테리어 스타일 조건조회

    @EntityGraph(attributePaths = "images")
    List<InteriorPost> findTop50ByStatusOrderByCreatedAtDesc(PostStatus status);
    //상태에 맞는 게시글중 최신순(CreatedAtDesc) 상위 50개 가져오기

    @EntityGraph(attributePaths = "images")
    List<InteriorPost> findTop20ByStatusOrderByLikeCountDescViewCountDescCreatedAtDesc(
            PostStatus status, Pageable pageable);
    //정렬 우선 순서
    //좋아요 desc
    //조회수 desc
    //최신수 desc
    //인기글 Top20

//    SELECT *
//    FROM interior_post
//    WHERE status = 'ACTIVE'
//    ORDER BY
//    like_count DESC,
//    view_count DESC,
//    created_at DESC
//    LIMIT 20

    @EntityGraph(attributePaths = {"images", "author"})
    Optional<InteriorPost> findByIdAndStatus(Long id, PostStatus status);
    // 게시글 상세보기 id+상태
    //"images", "author" 한번 가저옴
}

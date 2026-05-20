package com.example.roomfit.repository;

import com.example.roomfit.domain.CommunityBoardType;
import com.example.roomfit.domain.CommunityPost;
import com.example.roomfit.domain.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    // 게시판별 목록 조회
    @EntityGraph(attributePaths = "author")
    Page<CommunityPost> findByBoardTypeAndStatus(CommunityBoardType boardType, PostStatus status, Pageable pageable);

    // 게시글 상세 조회
    @EntityGraph(attributePaths = "author")
    Optional<CommunityPost> findByIdAndStatus(Long id, PostStatus status);

    long countByStatus(PostStatus status);
}

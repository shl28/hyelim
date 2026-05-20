package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "community_posts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CommunityBoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member author;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Builder.Default
    private int viewCount = 0;

    @Builder.Default
    private boolean answered = false; // 답변 완료 여부 (QNA게시판 용)

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PostStatus status = PostStatus.VISIBLE;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}

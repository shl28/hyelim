package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interior_posts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class InteriorPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InteriorStyle style;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private BigDecimal roomSize;
    private Integer budget;

    @Builder.Default
    private int viewCount = 0;

    @Builder.Default
    private int likeCount = 0;

    @Builder.Default
    private int commentCount = 0;

    @Builder.Default
    private boolean hasFurnitureTag = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PostStatus status = PostStatus.VISIBLE;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    @Builder.Default
    private List<PostImage> images = new ArrayList<>();

    public void addImage(PostImage image) { // 연관관계 편의 메서드
        images.add(image);
        image.setPost(this);
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    /** 목록/메인용 썸네일 (images 가 @EntityGraph 로 로드된 뒤 사용) */
    public String getThumbnailPath() {
        if (images == null || images.isEmpty()) { // 이미지 없으면 기본 이미지
            return "/images/no-image.svg";
        }
        String path = images.stream()
                .filter(PostImage::isThumbnail) // thumbnail - true(대표 썸네일 필터링)
                .map(PostImage::getFilePath) // 첫번쨰 유효한 filePath
                .filter(p -> p != null && !p.isBlank())
                .findFirst() // 있으면 추출
                .orElseGet(() -> images.stream() // thumbnail 지정 없으면 -> 아무 이미지나 첫장
                        .map(PostImage::getFilePath)
                        .filter(p -> p != null && !p.isBlank())
                        .findFirst() // 첫번째 이미지 추출
                        .orElse(null));
        if (path == null) {
            return "/images/no-image.svg"; // 데이터가 전부 유효하지 않다면 최종 반환
        }
        return path;

//        목록/메인용 썸네일 (images 가 @EntityGraph 로 로드된 뒤 사용)
//        public String getThumbnailPath() {
//            // 1. 이미지 컬렉션 자체가 비어있다면 바로 기본 이미지 반환
//            if (images == null || images.isEmpty()) {
//                return "/images/no-image.svg";
//            }
//
//            // 2. 우선순위 체이닝 시작
//            return images.stream()
//                    .filter(PostImage::isThumbnail)                 // ① 대표 썸네일 필터링
//                    .map(PostImage::getFilePath)
//                    .filter(p -> p != null && !p.isBlank())
//                    .findFirst()                                    // ② 있으면 추출
//                    .orElseGet(() -> images.stream()                // ③ [차선책] 대표 썸네일이 없다면 재탐색
//                            .map(PostImage::getFilePath)
//                            .filter(p -> p != null && !p.isBlank())
//                            .findFirst()                            // ④ 첫 번째 이미지 추출
//                            .orElse("/images/no-image.svg")         // ⑤ 데이터가 전부 유효하지 않다면 최종 반환
//                    );
//        }
    }
}

package com.example.roomfit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private Member member;

    // 방크기 8.5평 등 precision(전체 자리수) 4, scale(소수점 자리수) 1
    @Column(nullable = false, precision = 4, scale = 1)
    private BigDecimal roomSize;

    @Column(nullable = false)
    private Integer budget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private InteriorStyle preferredStyle;

    @Column(length = 50)
    private String lifestyle;

    @Builder.Default
    @Column(nullable = false)
    private boolean hasFurniture = false;

    @Column(length = 20)
    private String sleepPattern;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}

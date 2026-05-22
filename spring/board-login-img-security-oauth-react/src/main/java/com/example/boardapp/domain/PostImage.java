package com.example.boardapp.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String originalName;

    @Column(nullable = false, length = 255)
    private String storedName;

    @Column(nullable = false, length = 255)
    private String thumbnailName;

    @Column(nullable = false, length = 20)
    private String dateFolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    private PostImage(String originalName, String storedName, String thumbnailName, String dateFolder, Post post) {
        this.originalName = originalName;
        this.storedName = storedName;
        this.thumbnailName = thumbnailName;
        this.dateFolder = dateFolder;
        this.post = post;
    }
}

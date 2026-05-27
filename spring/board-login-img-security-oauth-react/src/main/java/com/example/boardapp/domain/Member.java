package com.example.boardapp.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 100)
    private String email;

    @Column(length = 30)
    private String oauthProvider;

    @Column(length = 100)
    private String oauthProviderId;

    @Builder
    private Member(String username, String password, String nickname,
                   String email, String oauthProvider, String oauthProviderId) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.oauthProviderId = oauthProviderId;
    }

    public void updateOAuthInfo(String email, String nickname) {
        this.email = email;
        if (nickname != null && !nickname.isBlank()) {
            this.nickname = nickname;
        }
    }
}

package com.example.boardapp.security;

import com.example.boardapp.domain.Member;

public class AuthPrincipalUtils {
    private AuthPrincipalUtils() {}

    public static Member extractMember(Object principal) {
        if (principal instanceof LoginUser loginUser) {
            return loginUser.getMember();
        }
        if (principal instanceof LoginOidcUser oidcUser) {
            return oidcUser.getMember();
        }

        throw new IllegalStateException("알 수 없는 인증 타입입니다.");
    }
}

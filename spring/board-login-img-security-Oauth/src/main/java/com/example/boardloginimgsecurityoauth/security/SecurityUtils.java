package com.example.boardloginimgsecurityoauth.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// 어디서든 가져다 사용 가능, private 실수로 객체 만들지 못하게 방지
// 매번 Parameter로 넘기지 않아도 되고, 필요할 때 어디서든 꺼내씀
// 세션에서 로그인한 사용자 정보를 꺼내는 유틸 클래스
public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //SecurityContextHolder → 현재 로그인 사용자 정보 꺼내는 도구
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
        //현재  로그인정보 가져오기
    }

    public static String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return auth.getName();
    }
}

// 로그인 전 : isAuthenticated() 호출 -> false 반환
// 로그인 후 : isAuthenticated() 호출 -> true 반환 , currentUsername() -> 로그인사용자 username 반환
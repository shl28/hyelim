package com.shop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

// 로그인 안된 경우 응답 방식
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
// 실제 인증 실패가 발생하면 commence 메서드 호출
// 파라미터 요청/응답 객체와 예외(AuthenticationException)가 들어옴

// 인증되지 않은 요청을 받으면 페이지 리다이렉트 같은 동작대신 401 Unauthorized 에러로 끝냄
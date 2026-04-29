package com.example.boardlogin.config;

import com.example.boardlogin.web.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
    private final LoginCheckInterceptor loginCheckInterceptor;

    public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/posts/write", "/posts/*/edit", "/posts/*/delete");
    }
}

// /posts : 로그인 필요 없음
// /posts/{id} : 로그인 필요 없음
// "/posts/write", "/posts/*/edit", "/posts/*/delete" : 로그인 필요

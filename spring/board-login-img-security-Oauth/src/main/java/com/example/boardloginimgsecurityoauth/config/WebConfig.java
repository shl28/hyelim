package com.example.boardloginimgsecurityoauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration

// 시큐리티 7에서는 LoginCheckInterceptor WebConfig에 안써도 됨, 별도 파일
public class WebConfig implements WebMvcConfigurer {
//    private final LoginCheckInterceptor loginCheckInterceptor;

    @Value("${app.upload-dir}")
    private String uploadDir;
    // application.properties 에서 파일 관리

//    public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
//        this.loginCheckInterceptor = loginCheckInterceptor;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path path = Path.of(uploadDir).toAbsolutePath().normalize();
        // .toAbsolutePath() 상대 경로 -> 절대경로 : ./upload -> c:/project/upload
        String location = path.toUri().toString();
        if (!location.endsWith("/")) {
            location += "/";
        }
        registry.addResourceHandler("/uploads/**")  // 브라우저가 요청하는 가상 주소
                .addResourceLocations(location);  // 실제 물리적 주소
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/posts/write", "/posts/*/edit", "/posts/*/delete");
//    }
}

// /posts : 로그인 필요 없음
// /posts/{id} : 로그인 필요 없음
// "/posts/write", "/posts/*/edit", "/posts/*/delete" : 로그인 필요

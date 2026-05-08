package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}

// 쇼핑몰/게시판등에서 자주 쓰임
// jpa 감사 기능 : 누가 만들었고, 누가 수정했는지
// 상품 등록을 누가 했는지 : createBy = "admin", "master"
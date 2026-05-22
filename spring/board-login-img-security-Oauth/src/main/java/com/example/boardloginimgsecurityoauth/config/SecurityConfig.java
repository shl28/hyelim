package com.example.boardloginimgsecurityoauth.config;

import com.example.boardloginimgsecurityoauth.security.CustomOAuth2UserService;
import com.example.boardloginimgsecurityoauth.security.CustomOidcUserService;
import com.example.boardloginimgsecurityoauth.security.LoginUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    public SecurityConfig(
            @Lazy CustomOAuth2UserService customOAuth2UserService,
            @Lazy CustomOidcUserService customOidcUserService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customOidcUserService = customOidcUserService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            LoginUserDetailsService loginUserDetailsService,
            // 명부관리자 - 사용자가 입력한 아이디를 보고 DB에서 해당 회원정보를 가진 객체 찾아오는 역할
            PasswordEncoder passwordEncoder) { // 암호 전문가- 사용자 입력 암호와 DB 암호 비교 판독
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(loginUserDetailsService);
        // 인증 처리(경비원)
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean // url 접근 권한 + 로그인/로그아웃 동작 정의
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider) throws Exception{

        http.authenticationProvider(authenticationProvider);
        // 인증provider 등록 -> LoginUserDetailsService + PasswordEncoder 조합 들어감
        // 로그인할 때 DB인증을 이 방식으로 함

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register", "/uploads/**", "/error").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers("/posts/write").authenticated()
                .requestMatchers("/posts/*/edit").authenticated()
                .requestMatchers(HttpMethod.POST, "/posts/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/posts").permitAll()  // 조회 누구나 가능
                .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                .requestMatchers("/home").authenticated()
                .anyRequest().authenticated());  // 명시하지 않은 URL -> 전부 로그인 필요

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error")
                .permitAll());

        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService)));

        http.logout(logout -> logout
                .logoutRequestMatcher(PathPatternRequestMatcher.pathPattern(HttpMethod.GET, "/logout"))
                .logoutSuccessUrl("/posts"));

        return http.build();
    }
}

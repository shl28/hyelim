package com.example.boardloginimgsecurity.security;

import com.example.boardloginimgsecurity.domain.User;
import com.example.boardloginimgsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class LoginUserDetailsService implements UserDetailsService {
    // 로그인 시 DB에서 사용자 정보 조회
    private final UserRepository userRepository;

    public LoginUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
        return new LoginUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getName());
    }
}

//로그인 요청 (/login)
//   ↓
//Spring Security
//   ↓
//           UserDetailsService.loadUserByUsername()
//   ↓
//DB 조회 (UserRepository)
//   ↓
//LoginUserDetails 반환
//   ↓
//비밀번호 비교
//   ↓
//성공 → 세션 저장
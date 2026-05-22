package com.example.boardapp.service;

import com.example.boardapp.domain.Member;
import com.example.boardapp.dto.MemberCreateRequest;
import com.example.boardapp.dto.MemberResponse;
import com.example.boardapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {
        if (memberRepository.existsByUsername(request.getUsername()))
            throw new IllegalStateException("이미 사용중인 아이디입니다.");

        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build();

        Member saved = memberRepository.save(member);

        return MemberResponse.from(saved);
    }
}

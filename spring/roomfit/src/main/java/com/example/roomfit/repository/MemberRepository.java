package com.example.roomfit.repository;

import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
//1. 로그인 인증처리/ 회원 정보 조회
    Optional<Member> findByEmail(String email);
//이메일로 아이디/비번찾기/중복검사
    Optional<Member> findByNameAndEmail(String name, String email);
//아이디찾기(이름과 이메일 일치여부)
    boolean existsByLoginId(String loginId);
//회원가입시 아이디 중복 확인
    boolean existsByEmail(String email);
//회원가입시 이메일 중복확인
    List<Member> findByStatus(MemberStatus status);
//특정상태 : 정지회원, 탈퇴회원 목록 조회
    long countByStatus(MemberStatus status);
    //대비보드 통계용(현재 활동 중인 회원 수 )
}

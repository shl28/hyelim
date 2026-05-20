package com.example.roomfit.repository;

import com.example.roomfit.domain.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 특정 repository 메서드에서만 강제로 EAGER 처럼 가져옴
    @EntityGraph(attributePaths = {"items", "items.product"})
    Optional<Cart> findWithItemsByMemberId(Long memberId);

    // findWithItemsByMemberId : 회원 Id로 Cart 조회
    // @EntityGraph 로 연관 데이터를 한번에 로드
    // items - CartItem 목록
    // items.product - 각 Item의 Product(이름, 가격, 이미지)

    Optional<Cart> findByMemberId(Long memberId);
}

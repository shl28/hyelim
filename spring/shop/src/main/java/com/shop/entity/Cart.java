package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString

public class Cart extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    // FetchType.LAZY : 필요할 때 가져오기
    // FetchType.EAGER : 기본값, 장바구니 조회할 때 무조건 member 가져옴, 무조건 JOIN 쿼리 발생
    // FetchType.LAZY : 장바구니만 가져옴, 필요할 때(cart.getMember().getName();), 호출할때 가져옴

    // 특정 회원(Member)을 위한 장바구니 공간을 처음 만들때 사용하는 정석 팩토리 메서드
    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        return cart; // 회원정보가 주입된 장바구니 객체를 반환
    }

}
// 장바구니 - 회원 관계
// 회원 1명 <-> 장바구니 1개
// cart 테이블 : member_id 컬럼 생기고 member 테이블의 기본키(pk) 가리키게 됨 -> FK(외래키)
// Cart 엔티티가 외래키를 보유하고 있으므로 이 연관관계의 주인이 됨
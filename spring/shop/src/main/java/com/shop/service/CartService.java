package com.shop.service;

import com.shop.dto.CartItemDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class CartService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDto cartItemDto, String email) {
        // 기본 정보 조회 : 주문하려는 상품이 존재하는지 확인
        // 현재 로그인한 회원(Member) 정보를 이메일 통해 가져온다
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);

        // 장바구니 존재 여부 체크 및 생성
        // 해당 회원의 장바구니(Cart)가 있는지 확인
        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null)  { // 처음 장바구니에 담는 사용자일 경우 createCart 메서드로 장바구니(Cart) 새로 만듦
             cart = Cart.createCart(member);
             cartRepository.save(cart);
        }

        // 장바구니 상품 중복 체크 및 처리
        // findByCartIdAndItemId : 해당 상품이 장바구니에 이미 담겨있는지 확인
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        // 이미 장바구니에 해당 상품이 있다면 addCount 메서드로 기존 수량에 더해줌(save 따로 없어도 더티체킹 통해 DB에 반영됨)
        if (savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else { // 장바구니에 없는 새로운 상품일 경우 createCartItem 메서드 통해 장바구니상품(CartItem) 객체 생성 후 저장
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }
}

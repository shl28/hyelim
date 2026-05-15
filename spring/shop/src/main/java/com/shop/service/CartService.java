package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
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
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

        if (cart == null) { // 처음 장바구니에 담는 사용자일 경우 createCart 메서드로 장바구니(Cart) 새로 만듦
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

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) return cartDetailDtoList; // 장바구니가 없으면 빈 리스트 반환

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        // 상품명, 가격, 수량, 대표이미지 반환받아 화면에 전달
        return cartDetailDtoList;
    }

    // 로그인한 사용자의 장바구니인지 확인
    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email) {
        Member curMember = memberRepository.findByEmail(email); // 로그인한 사용자 조회
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember(); // 장바구니 주인 조회

        if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) return false;
        // a.equals(b) 에러 -> NPE 발생 가능
        // StringUtils.equals : NPE 발생 X

        return true;
    }
//    return StringUtils.equals(
//            curMember.getEmail(),
//            savedMember.getEmail());

    public void updateCartItemCount(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);

        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);

        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) {
        List<OrderDto> orderDtoList = new ArrayList<>(); // 사용자가 체크한 장바구니 목록

        for (CartOrderDto cartOrderDto : cartOrderDtoList) { // 장바구니 상품 반복 조회(사용자가 선택한 장바구니 반복)
            CartItem cartItem = cartItemRepository // DB에서 장바구니 상품 조회
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderDto orderDto = new OrderDto(); // OrderDto 생성
            orderDto.setItemId(cartItem.getItem().getId()); // 상품 번호 저장
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
            // 장바구니 데이터 -> 주문용 Dto 로 변환

        }

        Long orderId = orderService.orders(orderDtoList, email);

        // 주문완료 후 장바구니 상품 비우기 : CartItem 재조회 후 삭제
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }
}

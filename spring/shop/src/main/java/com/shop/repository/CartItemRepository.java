package com.shop.repository;

import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // 사용자가 장바구니 상품을 담을 때 이미 이상품이 장바구니에 들어있는지 여부 판단
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}

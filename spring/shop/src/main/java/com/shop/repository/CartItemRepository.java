package com.shop.repository;

import com.shop.dto.CartDetailDto;
import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // 사용자가 장바구니 상품을 담을 때 이미 이상품이 장바구니에 들어있는지 여부 판단
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc ")
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
// select new com.shop.dto.CartDetailDto : jpQL에서 DTO 객체를 바로 생성하는 문법
// ci.id : 장바구니 상품 아이디 i.itemNm : 상품명 i.price : 상품 가격 ci.count : 장바구니 담은 수량 im.imgUrl : 대표사진 경로
// from CartItem ci(장바구니 상품 아이템), ItemImg im(상품이미지) 두개를 조회함

// join ci.item i  연관관계 매핑 조인
// CartItem 엔티티 안에 @ManyToOne @JoinColumn(name = item_id) private Item item
// sql로 변경 해보면 JOIN item i ON ci.item_id = i.id 랑 비슷

// CartItem -> Item 과 조인 - 대표이미지(ItemImg 연결) - DTO로 바로 변환 -  List<CartDetailDto> 반환

//SELECT
//ci.id,
//i.item_nm,
//i.price,
//ci.count,
//im.img_url
//FROM cart_item ci
//JOIN item i
//ON ci.item_id = i.id
//JOIN item_img im
//ON im.item_id = i.id
//WHERE ci.cart_id = ?
//AND im.repimg_yn = 'Y'
//ORDER BY ci.reg_time DESC;

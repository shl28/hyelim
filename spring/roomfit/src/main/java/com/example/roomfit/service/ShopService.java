package com.example.roomfit.service;

import com.example.roomfit.domain.*;
import com.example.roomfit.exception.BusinessException;
import com.example.roomfit.exception.ResourceNotFoundException;
import com.example.roomfit.repository.CartRepository;
import com.example.roomfit.repository.ProductRepository;
import com.example.roomfit.repository.ProductReviewRepository;
import com.example.roomfit.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ShopService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final WishlistRepository wishlistRepository;
    private final ProductReviewRepository productReviewRepository;
    private final MemberService memberService;
    private final UserProfileService userProfileService;

    public List<Product> listAll() {
        return productRepository.findByOnSaleTrueOrderByAvgRatingDesc();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("상품을 찾을 수 없습니다."));
    }

    public List<Product> recommendProducts(Long memberId) {
        UserProfile profile = userProfileService.findByMemberId(memberId);

        // 프로필 없을때 : 판매중 상품 전체에서 평점순으로 상위 8개
        if (profile == null) return productRepository.findByOnSaleTrueOrderByAvgRatingDesc().stream().limit(8).toList();

        // 프로필 있을때 : 같은 스타일 태그, 예산 이하 상품 정렬 평점 내림차순 상위 8개 상품
        int maxPrice = profile.getBudget() * 10000;

        return productRepository.findByOnSaleTrueAndStyleTagAndPriceLessThanEqualOrderByAvgRatingDesc(
                profile.getPreferredStyle(), maxPrice
        ).stream().limit(8).toList();
    }

    // 회원 장바구니 있으면 그대로 반환 / 없으면 Cart 새로 생성 후 저장
    @Transactional
    public Cart getOrCreateCart(Long memberId) {
        return cartRepository.findByMemberId(memberId).orElseGet(() -> {
            Member member = memberService.findById(memberId);
            return cartRepository.save(Cart.builder().member(member).build());
        });
    }

    @Transactional
    public void addToCart(Long memberId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(memberId); // 장바구니 조회. 없으면 생성
        Product product = getProduct(productId);

        // 이미 장바구니에 있는 상품인지 조회
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        // 없는 상품이면 새로 추가
        if (item == null)
            cart.getItems().add(CartItem.builder().cart(cart).product(product).quantity(quantity).build());
            // 이미 있는 상품이면 수량 증가
        else
            item.setQuantity(item.getQuantity() + quantity);
    }

//    @Transactional
//    public void addToCart(Long memberId, Long productId, int quantity) {
//
//        // 1. 장바구니 조회 (없으면 생성)
//        Cart cart = getOrCreateCart(memberId);
//
//        // 2. 상품 조회
//        Product product = getProduct(productId);
//
//        // 3. cart 안에 같은 상품이 있는지 찾기
//        CartItem foundItem = null;
//
//        for (CartItem cartItem : cart.getItems()) {
//
//            Long itemProductId = cartItem.getProduct().getId();
//
//            if (itemProductId.equals(productId)) {
//                foundItem = cartItem;
//                break;
//            }
//        }
//
//        // 4. 장바구니에 없으면 새로 추가
//        if (foundItem == null) {
//
//            CartItem newItem = new CartItem();
//
//            newItem.setCart(cart);
//            newItem.setProduct(product);
//            newItem.setQuantity(quantity);
//
//            cart.getItems().add(newItem);
//
//        }
//
//        // 5. 이미 있으면 수량 증가
//        else {
//
//            int currentQuantity = foundItem.getQuantity();
//
//            foundItem.setQuantity(
//                    currentQuantity + quantity
//            );
//        }
//    }

    // 장바구니 화면용 조회
    @Transactional
    public Cart getCartWithItems(Long memberId) {
        return cartRepository.findWithItemsByMemberId(memberId)
                .orElseGet(() -> getOrCreateCart(memberId));
    }

    @Transactional
    public void toggleWishlist(Long memberId, Long productId) {
        var existing = wishlistRepository.findByMemberIdAndProductId(memberId, productId);

        // 이미 위시리스트에 있다면 삭제(delete)
        if (existing.isPresent()) {
            wishlistRepository.delete(existing.get());
            return;
        }

        Member member = memberService.findById(memberId);
        Product product = getProduct(productId);

        // 위시리스트 엔티티에 추가
        wishlistRepository.save(Wishlist.builder().member(member).product(product).build());
    }

    // 위시리스트 여부 확인 - 상품 상세페이지에서 버튼 표시
    public boolean isWished(Long memberId, Long productId) {
        return wishlistRepository.existsByMemberIdAndProductId(memberId, productId);
    }

    @Transactional
    public void addReview(Long memberId, Long productId, int rating, String content) {
        if (rating < 1 || rating > 5)
            throw new BusinessException("평점은 1~5 사이입니다.");

        Product product = getProduct(productId);
        Member member = memberService.findById(memberId);

        if (productReviewRepository.findByProductIdAndMemberId(productId, memberId).isPresent())
            throw new BusinessException("이미 리뷰를 작성했습니다.");

        productReviewRepository.save(ProductReview.builder()
                .product(product)
                .member(member)
                .rating(rating)
                .content(content)
                .build());

        recalculateRating(product);
    }

    private void recalculateRating(Product product) {
        List<ProductReview> reviews = productReviewRepository.findByProductIdOrderByCreatedAtDesc(product.getId());
        double avg = reviews.stream().mapToInt(ProductReview::getRating).average().orElse(0);
        product.setAvgRating(avg);
        product.setReviewCount(reviews.size());
    }

    public List<ProductReview> getReviews(Long productId) {
        return productReviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
}

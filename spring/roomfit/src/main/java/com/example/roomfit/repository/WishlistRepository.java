package com.example.roomfit.repository;

import com.example.roomfit.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    boolean existsByMemberIdAndProductId(Long memberId, Long productId);

//    SELECT COUNT(*) > 0 FROM wishlists WHERE member_id = ? AND product_id = ?

    Optional<Wishlist> findByMemberIdAndProductId(Long memberId, Long productId);

    List<Wishlist> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}

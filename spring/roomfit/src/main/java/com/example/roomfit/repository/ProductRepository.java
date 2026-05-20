package com.example.roomfit.repository;

import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOnSaleTrueAndStyleTagAndPriceLessThanEqualOrderByAvgRatingDesc(
            InteriorStyle styleTag, int maxPrice);

//    SELECT *
//    FROM product
//    WHERE on_sale = true
//    AND style_tag = ?
//    AND price <= ?
//    ORDER BY avg_rating DESC;

//    List<Product> products =
//            productRepository
//                    .findByOnSaleTrueAndStyleTagAndPriceLessThanEqualOrderByAvgRatingDesc(
//                            InteriorStyle.MODERN,
//                            100000
//                    );

    // 판매중 상품 전체, 평점 내림 차순
    List<Product> findByOnSaleTrueOrderByAvgRatingDesc();

    Optional<Product> findByName(String name);

    Page<Product> findAllByOrderByIdDesc(Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseOrderByIdDesc(String name, Pageable pageable);
}

package com.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 엔티티가 DB에 들어가거나 수정될때, JPA가 리스너를 통해 자동으로 시간 주입
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass  // 상속받은 자식 엔티티에게만 매핑정보 제공
@Getter
@Setter
// abstract: 반드시 상품(Item) 이나 주문(Order) 처럼 실제 엔팅티에 상속되어야만 의미 있음
// 실수로 생성하지 못하도록 추상클래스로 만듦
public abstract class BaseTimeEntity {
    // createdAt, updatedAt

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}

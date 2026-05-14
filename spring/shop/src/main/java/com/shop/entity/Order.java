package com.shop.entity;

import com.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter

public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // 속성값 order : OrderItem 에 있는 Order 에 의해 관리됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // CascadeType.ALL : 게시글 전체 댓글 삭제
//    orphanRemoval = true : 댓글 리스트에서 특정 댓글 삭제
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); // Order 내의 리스트에 추가
        orderItem.setOrder(this); // OrderItem 의 현재 Order 설정
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);  // 1. 주문한 회원 정보 설정

        // 2. 여러개의 주문 상품(OrderItem)을 주문 객체에 연결
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
            // addOrderItem 메서드는 단순히 리스트에 담는것을 넘어
            // orderItem 객체에도 Order정보를 세팅해주는 양방향 연관관계 편의 메서드

            order.setOrderStatus(OrderStatus.ORDER); // 3. 주문 상태를 ORDER로 초기화
            order.setOrderDate(LocalDateTime.now()); // 4. 현재 시간을 주문시간으로 설정

        }
        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

//    Item: 개당 가격 정보
//    OrderItem: 상품가격 X 수량
//    Order: 각 OrderItem의 결과값

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); // 재고수량 원복
        }
    }
}

package com.example.crud2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// @Entity : 이 타입은 영속성 대상이고, 테이블과 매핑
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DoIt {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB가 자동 생성 auto_increment와 같음
    private Long num;

    @Column // 테이블 컬럼 : 생략 가능
    private String title;

    @Column
    private String content;
}

//CREATE TABLE do_it (
//        num BIGINT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255),
//        content VARCHAR(255)
//);
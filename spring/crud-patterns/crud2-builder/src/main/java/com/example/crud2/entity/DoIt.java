package com.example.crud2.entity;

import com.example.crud2.dto.DoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//jpa 엔티티 생성시 기본생성자가 필요 - 외부에서 무분별한 생성을 막기위해  PROTECTED
//@AllArgsConstructor

public class DoIt {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db자동생성 auto increment
    private Long num;

    @Column //태이블 컬럼 - 생략가능
    private String title;

    @Column
    private String content;


    @Builder
    public  DoIt(String title, String content){
        this.title = title;
        this.content = content;
    }
    public static DoIt from(DoDto dto){
        return DoIt.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
//@Entity -이타입은 영속성 대상이고, 테이블 매핑-

//CREATE TABLE do_it (
//        num BIGINT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(255),
//        content VARCHAR(255)
//);

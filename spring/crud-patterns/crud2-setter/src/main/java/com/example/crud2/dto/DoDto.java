package com.example.crud2.dto;


import com.example.crud2.entity.DoIt;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoDto {
    private Long num;
    private String title;
    private String content;

    //dto -> entity
//    public DoIt toEntity(){
//        return new DoIt(num, title, content);
//    }

}

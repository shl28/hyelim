package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter

public class ItemImgDto {
    private Long id;

    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

    // static : 매번 객체 생성하지 않기 위해
    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
//ItemImg 엔티티 객체를 ItemImgDto 객체로 변환
//ItemImgDto dto = new ItemImgDto();
//dto.setId(itemImg.getId());
//        dto.setImgName(itemImg.getImgName());
//        dto.setOriImgName(itemImg.getOriImgName());
//        dto.setImgUrl(itemImg.getImgUrl());

// return modelMapper.map(itemImg, ItemImgDto.class);
// 서비스 에서 아래와 같이 사용 가능
//ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
package com.example.roomfit.dto;

import com.example.roomfit.domain.InteriorStyle;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder

// RecommendEngine 이 계산한 결과를 controller -> thymeleaf 로 넘기는 한덩어리 응답모델
public class RecommendResultDto {

    private final List<ScoredPostDto> posts;
    private final List<String> colorPalette; // (hex색 4개 - 스타일별)
    private final String layoutAdvice; // 배치 및 생활패턴
    private final InteriorStyle preferredStyle; // 선호 스타일
}

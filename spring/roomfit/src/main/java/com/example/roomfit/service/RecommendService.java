package com.example.roomfit.service;

import com.example.roomfit.dto.RecommendResultDto;
import com.example.roomfit.recommend.RecommendEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class RecommendService {
    // recommendEngine - 복잡한 알고리즘 계산 수행
    // RecommendService - 비즈니스 요구사항 처리, 외부에서 엔진 기능 쓸수있도록 함

    private final RecommendEngine recommendEngine;

    public RecommendResultDto getRecommendations(Long memberId) {
        return recommendEngine.recommend(memberId);
    }
}

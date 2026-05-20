package com.example.roomfit.domain;

public enum ReportStatus {
    PENDING, PROCESSED, REJECTED
}

// 신고가 관리자 하에 어떤단계인지 나타냄
// PENDING : 대기 - 신고접수되었으나 미처리 상태 - 기본값
// PROCESSED : 처리완료 - 관리자 검토후 조치완료(삭제, 경고 등)
// REJECTED : 반려 - 신고내용 기각

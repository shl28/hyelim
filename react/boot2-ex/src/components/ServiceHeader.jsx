import React from "react";

function ServiceHeader() {
    return (
        <section className="py-5 text-center">
            <div className="container">
                {/* 배지 */}
                <span className="badge bg-primary mb-3">Services</span>

                {/* 제목 */}
                <h1 className="fw-bold mb-3">서비스 소개</h1>

                {/* 설명 */}
                <p className="text-muted">
                    Boot2 예제에서 사용한 기술 스택을 플랜 형태로 정리했습니다.
                </p>
            </div>
        </section>
    );
}

export default ServiceHeader;

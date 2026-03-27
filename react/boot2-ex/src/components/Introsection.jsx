import React from "react";
import { Link } from "react-router-dom";

function Introsection() {
    return (
        <section className="py-5">
            <div className="container">
                <div className="row align-items-center">
                    <div className="col-md-6">
                        <h2 className="fw-bold mb-3">
                            Boot2 데모에 오신 것을 환영합니다
                        </h2>
                        <p className="text-muted mb-4">
                            상단 네비게이션, Slick 메인 슬라이드, 이미지 카드,
                            푸터가 포함되어 있습니다. 아래 링크로 서비스
                            페이지를 확인해 보세요.
                        </p>

                        <Link to="/services" className="btn btn-primary me-2">
                            서비스 소개
                        </Link>

                        <Link
                            to="/contact"
                            className="btn btn-outline-secondary"
                        >
                            문의하기
                        </Link>
                    </div>

                    <div className="col-md-6 py-3">
                        <img
                            src="https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=900&q=80"
                            alt="intro"
                            className="img-fluid rounded shadow-sm"
                        />
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Introsection;

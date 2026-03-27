import React from "react";
import { Link } from "react-router-dom";

function Footer() {
    return (
        <footer className="footer py-5 text-white">
            <div className="container">
                <div className="row g-4">
                    <div className="col-md-4">
                        <h5 className="fw-bold">Boot2</h5>
                        <p className="text-white small">
                            React + Bootstrap + Slick 슬라이더로 만든 데모
                            사이트입니다.
                        </p>
                    </div>

                    <div className="col-md-4">
                        <h6 className="fw-bold mb-3">바로가기</h6>
                        <ul className="list-unstyled small">
                            <li className="mb-2">
                                <Link
                                    to="/"
                                    className="text-white-50 text-decoration-none"
                                >
                                    홈
                                </Link>
                            </li>
                            <li className="mb-2">
                                <Link
                                    to="/services"
                                    className="text-white-50 text-decoration-none"
                                >
                                    서비스
                                </Link>
                            </li>
                            <li className="mb-2">
                                <Link
                                    to="/contact"
                                    className="text-white-50 text-decoration-none"
                                >
                                    문의
                                </Link>
                            </li>
                        </ul>
                    </div>

                    <div className="col-md-4">
                        <h6 className="fw-bold mb-3">연락</h6>
                        <p className="text-white-50 small mb-1">
                            ✉ hello@boot2.example
                        </p>
                        <p className="text-white-50 small">
                            📍 서울시 · 데모 주소
                        </p>
                    </div>
                </div>

                <hr className="border-secondary mt-4" />

                <div className="text-center small text-white-50">
                    © 2026 Boot2. 교육용 예제입니다.
                </div>
            </div>
        </footer>
    );
}

export default Footer;

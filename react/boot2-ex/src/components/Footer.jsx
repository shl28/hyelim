import React from "react";

function Footer() {
    return (
        <footer className="py-4 bg-dark text-white-50">
            <div className="container">
                <div className="row-g-4">
                    <div className="col-md-4">
                        <h4>Boot2</h4>
                        <p>
                            React + Bootstrap + Slick 슬라이더로 만든 데모
                            사이트입니다.
                        </p>
                    </div>
                    <div className="col-md-4">
                        <h4>바로가기</h4>
                        <ul className="list-unstyled small">
                            <li className="mb-2">
                                <a href="/">홈</a>
                            </li>
                            <li className="mb-2">
                                <a href="/services">서비스</a>
                            </li>
                            <li className="mb-2">
                                <a href="/contact">문의</a>
                            </li>
                        </ul>
                    </div>
                    <div className="col-md-4">
                        <h4>연락</h4>
                        <p>hello@boot2.example</p>
                        <p>서울시 · 데모 주소</p>
                    </div>
                </div>
            </div>
        </footer>
    );
}

export default Footer;

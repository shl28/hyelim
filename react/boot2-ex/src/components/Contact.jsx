import React from "react";
import { Link } from "react-router-dom";
import { FaRegBuilding, FaRegClock } from "react-icons/fa";
import { FiPhone } from "react-icons/fi";
import "./Contact.css";

function Contact() {
  return (
    <section className="py-5">
      <div className="container">
        <div className="row g-5">
          <div className="col-md-5">
            <h2 className="fw-bold mb-3">문의하기</h2>
            <p className="text-muted mb-4">
              데모 폼입니다. 제출해도 실제 서버로 전송되지 않으며, 성공 메시지만
              표시됩니다.
            </p>
            <p className="mb-2 text-muted">
              <FaRegBuilding className="text-primary" /> Boot2 데모 스튜디오
            </p>
            <p className="mb-2 text-muted">
              <FiPhone className="text-primary" /> 02-0000-0000
            </p>
            <p className="mb-4 text-muted">
              <FaRegClock className="text-primary" /> 평일 09:00 - 18:00
            </p>
            <hr />
            <div className="contact-links mt-3">
              <Link to="/services">← 서비스 소개</Link>
              <span className="divider">|</span>
              <Link to="/">홈으로</Link>
            </div>
          </div>
          <div className="col-md-7">
            <div className="card shadow-sm p-4 border-0">
              <form>
                <div className="mb-3">
                  <label className="form-label">이름</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="홍길동"
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">이메일</label>
                  <input
                    type="email"
                    className="form-control"
                    placeholder="you@example.com"
                  />
                </div>
                <div className="mb-4">
                  <label className="form-label">메시지</label>
                  <textarea
                    className="form-control"
                    rows="5"
                    placeholder="문의 내용을 입력하세요"
                  ></textarea>
                </div>
                <button className="btn btn-primary w-100">보내기</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Contact;

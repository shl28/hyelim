import React from "react";
import { Link } from "react-router-dom";

function ServiceCTA() {
  return (
    <section className="py-5">
      <div className="container">
        <div className="cta-box text-center p-5 rounded">
          <h3 className="fw-bold mb-3">다음 단계가 궁금하다면</h3>
          <p className="text-muted mb-4">
            문의 페이지에서 간단한 폼 예제도 함께 확인할 수 있습니다.
          </p>
          <Link to="/contact" className="btn btn-primary px-4 py-2">
            문의 페이지로 이동
          </Link>
        </div>
      </div>
    </section>
  );
}

export default ServiceCTA;

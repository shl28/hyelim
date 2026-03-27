import React from "react";
import ServiceCard from "./ServiceCard";

function ServiceList() {
  const services = [
    {
      title: "스타터",
      price: "₩0",
      desc: "학습용 기본 템플릿",
      features: ["반응형 레이아웃", "네비 + 푸터", "슬라이드 1종"],
    },
    {
      title: "프로",
      price: "문의",
      desc: "실서비스에 가까운 구성",
      recommended: true,
      features: ["페이지 분리 라우팅", "이미지 카드", "Slick 옵션 커스텀"],
    },
    {
      title: "엔터프라이즈",
      price: "별도",
      desc: "팀 단위 커스터마이징",
      features: ["API 연동", "인증", "배포 파이프라인"],
    },
  ];
  return (
    <section className="py-5">
      <div className="container">
        <div className="row g-4">
          {services.map((service, index) => (
            <ServiceCard key={index} {...service} />
          ))}
        </div>
      </div>
    </section>
  );
}

export default ServiceList;

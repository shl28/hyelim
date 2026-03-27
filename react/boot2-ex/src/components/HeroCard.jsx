import React from "react";

function HeroCard() {
    const cards = [
        {
            id: 1,
            img: "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&q=80",
            title: "UI 구축",
            desc: "Bootstrap 그리드와 컴포넌트로 빠르게 레이아웃을 잡습니다.",
        },
        {
            id: 2,
            img: "https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&q=80",
            title: "라우팅",
            desc: "React Router로 홈·서비스·문의 페이지를 연결했습니다.",
        },
        {
            id: 3,
            img: "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80",
            title: "슬라이더",
            desc: "react-slick으로 메인 히어로 배너를 구성했습니다.",
        },
    ];

    return (
        <section className="py-5 bg-light">
            <div className="container">
                {/* 제목 */}
                <div className="text-center mb-5">
                    <h2 className="fw-bold">주요 구성</h2>
                    <p className="text-muted">
                        이미지 카드로 핵심 기능을 소개합니다.
                    </p>
                </div>

                {/* 카드 영역 */}
                <div className="row g-4">
                    {cards.map((card) => (
                        <div key={card.id} className="col-md-4">
                            <div className="card h-100 shadow-sm">
                                <img
                                    src={card.img}
                                    className="card-img-top"
                                    alt={card.title}
                                    style={{
                                        height: "200px",
                                        objectFit: "cover",
                                    }}
                                />

                                <div className="card-body d-flex flex-column">
                                    <h5 className="card-title">{card.title}</h5>

                                    <p className="card-text text-muted flex-grow-1">
                                        {card.desc}
                                    </p>

                                    <button className="btn btn-outline-primary btn-sm align-self-start">
                                        자세히
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    );
}

export default HeroCard;

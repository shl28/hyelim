import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Autoplay } from "swiper/modules";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "./ImageSlider.css";

function ImageSlider() {
    const images = [
        {
            id: 1,
            src: "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1600&q=80",
            title: "빠르고 안정적인 웹",
            desc: "React와 Bootstrap으로 구성하는 모던 UI",
            btn: "서비스 보기 →",
        },
        {
            id: 2,
            src: "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1600&q=80",
            title: "슬릭(Slick) 슬라이더",
            desc: "메인 배너에 자동 재생 · 화살표 · 도트 인디케이터",
            btn: "문의하기 →",
        },
        {
            id: 3,
            src: "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1600&q=80",
            title: "카드 & 이미지",
            desc: "Unsplash 이미지를 카드 레이아웃으로 구성했습니다.",
            btn: "서비스 보기 →",
        },
    ];

    return (
        <Swiper
            modules={[Navigation, Pagination, Autoplay]}
            spaceBetween={0}
            slidesPerView={1}
            loop={true}
            navigation
            pagination={{ clickable: true }}
            autoplay={{
                delay: 3000,
                disableOnInteraction: false,
            }}
        >
            {images.map((image) => (
                <SwiperSlide key={image.id}>
                    <div className="image-slide">
                        <img src={image.src} alt={image.title} />
                        <div className="slide-overlay"></div>
                        <div className="slide-inner">
                            <div className="container">
                                <div className="slide-content text-white">
                                    <h1 className="fw-bold">{image.title}</h1>
                                    <p>{image.desc}</p>
                                    <button className="btn btn-primary me-2">
                                        {image.btn}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </SwiperSlide>
            ))}
        </Swiper>
    );
}

export default ImageSlider;

// 슬라이드 사진 1
// https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1600&q=80

// 슬라이드 사진 2
// https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1600&q=80

// 슬라이드 사진 3
// https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1600&q=80

// 주요구성 카드 1
// https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&q=80

// 주요구성 카드 2
// https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&q=80

// 주요구성 카드3
// https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80

// 환영합니다 사진
// https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=900&q=80

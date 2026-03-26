// import React from 'react'
// import SwiperSlideComponent from "./pages/SwiperSlide";

// function Hero() {
//   return (
//     <div className='container'>
//         <div className="swiper-container">
//             <Swiper
//               modules={[Navigation, Pagination, Autoplay]}
//               spaceBetween={20}
//               slidesPerView={3} //한화면 몇장표시
//               slidesPerGroup={1} //한번이동시 갯수
//               navigation //좌우화살표
//               pagination={{ clickable: true }} //하단 pager 점클릭가능
//               autoplay={{
//                 //자동슬라이드
//                 delay: 3000,
//                 disableOnInteraction: false,
//               }}
//               breakpoints={{
//                 320: {
//                   slidesPerView: 1,
//                   spaceBetween: 10,
//                 },
//                 768: {
//                   slidesPerView: 2,
//                   spaceBetween: 15,
//                 },
//                 992: {
//                   slidesPerView: 3,
//                   spaceBetween: 20,
//                 },
//               }}
//             >
//               {images.map((image) => (
//                 <SwiperSlide key={image.id}>
//                   <div className="swiper-slide-item">
//                     <img
//                       src={image.src}
//                       alt={image.alt}
//                       className="img-fluid w-100"
//                       style={{ height: "300px", objectFit: "cover" }}
//                     />
//                     <div className="swiper-caption mt-2">
//                       <h6>{image.alt}</h6>
//                     </div>
//                   </div>
//                 </SwiperSlide>
//               ))}
//             </Swiper>
//           </div>
//     </div>
//   )
// }

// export default Hero

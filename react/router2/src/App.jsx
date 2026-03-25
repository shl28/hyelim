import React from "react";
import { Route, Routes, Link } from "react-router-dom";
import Home from "./pages/Home";
import ProductList from "./pages/ProductList";
import ProductDetail from "./pages/ProductDetail";

function App() {
    return (
        <div>
            <nav>
                <Link to="/">홈</Link> | {""}
                <Link to="/products">상품목록</Link>
            </nav>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/products" element={<ProductList />} />
                <Route path="/product/:id" element={<ProductDetail />} />
            </Routes>
        </div>
    );
}

export default App;

// BrowserRouter (라우터관리자) : main.jsx(vite) 나 index.js(cda)
// Routes / Route:  url 컴포넌트 연결
// path : url 주소
// element : 보여줄 페이지 컴포넌트
// Link : 페이지 이동  , a 태그 대체
// useNavigate : 코드로 페이지 이동 | -1: 뒤로가기 | 1: 앞으로 가기
// navigate("/about") : about 으로 이동

// useParams : url parameter 가져오기

// ===========================================================================================================================
// useLocation : 현재의 url 정보 가져오기
// 사용 예시
// import { useLocation } from "react-router-dom";

// const location = useLocation();

// console.log(location.pathname); // 현재 경로

// useSearchParams:
// 사용 예시
// import { useSearchParams } from "react-router-dom";

// const [params, setParams] = useSearchParams();

// const page = params.get("page"); // ?page=1
// /products?page=2

// setParams({page: 3})
// 결과: /products?page=3

// Outlet: 중첩 라우팅 - 레이아웃 안에서 페이지 바뀌는 자리
// 사용 예시

// import { Outlet } from "react-router-dom";

// function Layout() {
//   return (
//     <div>
//       <h1>공통 레이아웃</h1>
//       <Outlet />
//     </div>
//   );
// }

// Navigate(리다이렉트) - 강제 이동
// 사용 예시 : 로그인 안했을때 로그인 페이지로 강제 이동 등

// import { Navigate } from "react-router-dom";

// return <Navigate to="/login" />;

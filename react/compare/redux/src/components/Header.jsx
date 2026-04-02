import React from "react";
import { useSelector } from "react-redux";

export default function Header() {
    const user = useSelector((state) => state.user);
    // useSelector : redux store 에서 데이터를 꺼내는 훅
    // store 에 있는 state 읽기 -> 값이 바뀌면 자동 렌더링

    return (
        <header className="header">
            <h1>⚛️ Redux 방식</h1>
            <p>
                현재 사용자: {user.name} ({user.age}세)
            </p>
        </header>
    );
}

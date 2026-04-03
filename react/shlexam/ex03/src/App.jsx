import React from "react";
import { useState } from "react";

function App() {
    const [isLogin, setIsLogin] = useState(false);

    const onToggle = () => {
        setIsLogin((prev) => !prev);
    };

    return (
        <div>
            <button onClick={onToggle}>
                {isLogin ? "로그아웃" : "로그인"}
            </button>
            {isLogin ? <p>환영합니다</p> : <p>로그인이 필요합니다</p>}
        </div>
    );
}

export default App;

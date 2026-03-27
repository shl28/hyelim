import React, { useState } from "react";
import Toolbar from "./components/Toolbar";
import ContentPanel from "./components/ContentPanel";

function App() {
    const [theme, setTheme] = useState("light");
    const [count, setCount] = useState(0);

    const toggleTheme = () => {
        setTheme((t) => (t === "light" ? "dark" : "light"));
    };

    return (
        <div className={`app ${theme}`}>
            <header>
                <h1>Props 예제</h1>
                <span className="badge">상태는 App · Props로 전달</span>
            </header>
            <Toolbar
                theme={theme}
                onToggleTheme={toggleTheme}
                count={count}
                onIncrement={() => setCount((c) => c + 1)}
            />
            <ContentPanel theme={theme} count={count} />
        </div>
    );
}

export default App;

// App 에서 props로 전달
// theme : 현재 테마
// onToggleTheme : 테마 변경 함수
// count : 현재 숫자
// onIncrement : 숫자 증가 함수
// Toolbar : 조작 담당, App의 state 변경
// Toolbar 상태를 직접 바꾸지 X -> App에 요청만 함
// ContentPanel : 출력 담당

// 전체 흐름
// 버튼 클릭 -> App 함수 실행 -> state 변경 -> props -> Toolbar/ContentPanel 자동 업데이트

import React, { createContext, useContext, useState, useMemo } from "react";

// props 제거용
// props 없이 어디서든 상태 사용하기 가능

const AppContext = createContext(null);
// 전역 상태를 저장할 공간 생성 (Provider가 실제값을 넣어줌)

export function AppProvider({ children }) {
    const [theme, setTheme] = useState("light");
    const [count, setCount] = useState(0);
    // 전역으로 사용할 내용
    // theme : 라이트 | 다크
    // count : 숫자 카운트

    // 상태 변경 함수 : 테마 변경
    const toggleTheme = () => {
        setTheme((t) => (t === "light" ? "dark" : "light"));
    };

    // 상태 변경 함수 : 숫자(카운트) 변경
    const increment = () => setCount((c) => c + 1);

    // context 값이 바뀌면 모든 자식 컴포넌트 리렌더링
    // useMemo : 의존성 배열 내 [theme, count] 변경 시에만 리렌더링 하여 value 값 변경
    const value = useMemo(
        () => ({
            theme,
            count,
            toggleTheme,
            increment,
        }),
        [theme, count],
    );

    return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
    // AppProvider() 앱 전체를 감싸서 상태를 공유
}

export function useApp() {
    const ctx = useContext(AppContext);
    // Context 값을 가져오기

    if (!ctx) {
        // Provider 밖에서 쓰면 에러
        throw new Error("useApp 는 AppProvider 안에서만 사용하세요.");
    }

    return ctx; // Context 값 리턴
}

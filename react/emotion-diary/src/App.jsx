import React, { useEffect, useState } from "react";
import DiaryForm from "./components/DiaryForm";
import DiaryList from "./components/DiaryList";

const STORAGE_KEY = "emotion-diary-entries";

// 저장된 일기 불러오기
function loadEntries() {
    try {
        const raw = localStorage.getItem(STORAGE_KEY); // localStorage 에서 문자열 가져와 raw에 저장
        return raw ? JSON.parse(raw) : []; // raw 가 JSON 일 시 사용가능한 객체로 변환, 없으면 빈배열 반환
    } catch {
        return [];
        // JSON 깨지거나 이상할때 앱 터지는것을 방지
    }
}

function saveEntries(entries) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(entries));
    // stringify : 객체 -> json으로 변환하여 저장
}

function App() {
    // 처음 실행 시 loadEntries() 실행 -> 기존 데이터 있으면 불러오기
    const [entries, setEntries] = useState(loadEntries);

    // entries가 바뀔 때마다 실행됨
    useEffect(() => {
        saveEntries(entries); // entries 바뀔때마다 loadEntries에 저장됨
    }, [entries]);

    const addEntry = (data) => {
        const entry = {
            id: crypto.randomUUID(), // 고유 id 생성
            emotion: data.emotion,
            content: data.content,
            createdAt: Date.now(),
        };
        setEntries((prev) => [entry, ...prev]);
    };

    const deleteEntry = (id) => {
        setEntries((prev) => prev.filter((e) => e.id !== id));
    };

    return (
        <div className="app">
            <header className="header">
                <h1>
                    <span className="header-icon">📔</span>
                    <p className="tagline">
                        오늘의 감정을 기록하고 마음을 정리해보세요
                    </p>
                </h1>
            </header>
            <main className="main">
                <section className="write-section">
                    <h2>오늘의 일기</h2>
                    <DiaryForm onSubmit={addEntry} />
                </section>
                <section className="history-section">
                    <h2>나의 일기 모음</h2>
                    <DiaryList entries={entries} onDelete={deleteEntry} />
                </section>
            </main>
        </div>
    );
}

export default App;

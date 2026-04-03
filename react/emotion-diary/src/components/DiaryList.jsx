import React from "react";
import DiaryEntry from "./DiaryEntry";

function DiaryList({ entries, onDelete }) {
    // entries : 전체 데이터 배열  | onDelete : 삭제함수
    if (entries.length === 0) {
        // 데이터 없으면 아래 UI 출력
        return (
            <div className="diary-empty">
                <p className="empty-emoji">📔</p>
                <p>아직 작성한 일기가 없어요</p>
                <p>첫 번째 감정을 기록해보세요</p>
            </div>
        );
    }

    return (
        <div className="diary-list">
            {entries
                .slice() // React는 불변성이 중요! slice()-사본 생성
                .sort((a, b) => b.createdAt - a.createdAt) // 정렬 : 복사 후 정렬 하는이유- 정렬만 할 경우, 원본 배열이 바뀌게 됨
                .map((entry) => (
                    <DiaryEntry
                        key={entry.id}
                        entry={entry}
                        onDelete={onDelete}
                    />
                ))}
        </div>
    );
}

export default DiaryList;

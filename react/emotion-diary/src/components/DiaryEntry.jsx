import React from "react";
import { EMOTIONS } from "../constants";

// entry 하나를 받아 감정, 날짜, 내용, 삭제버튼 보여주는 컴포넌트
function DiaryEntry({ entry, onDelete }) {
    const emotionData = EMOTIONS.find((e) => e.id === entry.emotion);
    // 현재 일기의 감정정보를 가져옴
    // 예시
    // entry.emotion -> happy
    // EMOTIONS = { id: "happy", label: "행복", emoji: "😊", color: "#fcd34d" }, 기 emotionData에 담김

    const formatDate = (timestamp) => {
        // 시간을 '오늘/날짜' 형태로 변경
        const d = new Date(timestamp);
        const now = new Date();
        const isToday = d.toDateString() === now.toDateString(); // 같은 날짜면 -> 오늘
        return isToday
            ? `오늘 ${d.toLocaleTimeString("ko-KR", { hour: "2-digit", minute: "2-digit" })}` // 오늘일 경우, '오늘 시간 분' 만 표시
            : d.toLocaleDateString("ko-KR", {
                  month: "long",
                  day: "numeric",
                  hour: "2-digit",
                  minute: "2-digit",
                  // 오늘이 아닐 경우, '월 일 시간 분' 표시
              });
    };

    return (
        <article className="diary-entry">
            <div className="entry-header">
                <span
                    className="entry-emotion"
                    style={{ backgroundColor: emotionData?.color || "#e5e7eb" }} // color 없을 경우 "#e5e7eb" 적용
                    title={emotionData?.label} // label 없으면 없는대로 둠
                >
                    {emotionData?.emoji || "📝"}
                    {/* ?. : 안전접근  원래 없을 경우 error 나지만 없어도 error 없음 Optional과 비슷 */}
                </span>
                <span className="entry-date">
                    {formatDate(entry.createdAt)}
                </span>
                <button
                    type="button"
                    className="entry-delete"
                    onClick={() => onDelete(entry.id)}
                    title="삭제"
                >
                    X
                </button>
            </div>
            <p className="entry-content">{entry.content}</p>
        </article>
    );
}

export default DiaryEntry;

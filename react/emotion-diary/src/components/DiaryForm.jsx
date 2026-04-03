import React, { useState } from "react";
import EmotionPicker from "./EmotionPicker";

function DiaryForm({ onSubmit }) {
    const [emotion, setEmotion] = useState("");
    const [content, setContent] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!emotion || !content.trim()) return; // 감정 선택 안되었거나, 내용이 없다면 return

        onSubmit({ emotion, content: content.trim() }); // 부모에게 데이터 전달 DiaryForm -> App addEntry() 로 연결

        setEmotion("");
        setContent("");
    };

    return (
        <form className="diary-form" onSubmit={handleSubmit}>
            <EmotionPicker value={emotion} onChange={setEmotion} />
            <div className="form-group">
                <textarea
                    placeholder="오늘 하루는 어땠나요? 마음을 적어보세요..."
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    rows={5}
                />
            </div>
            <button
                type="submit"
                disabled={!emotion || !content.trim()}
                className="submit-btn"
            >
                일기저장하기
            </button>
        </form>
    );
}

export default DiaryForm;

import React, { useCallback, useEffect, useRef, useState } from "react";

function CardForm({ onSubmit, onCancel, placeholder }) {
    const [text, setText] = useState("");

    const textareaRef = useRef(null); // 마운트 시 focus 호출

    useEffect(() => {
        textareaRef.current?.focus();
        // ?: 값이 있으면 실행, 없으면 실행 X
    }, []);

    const handleSubmit = useCallback(
        (e) => {
            e.preventDefault();
            onSubmit(text);
        },
        [text, onSubmit],
    );

    return (
        <form className="card-form" onSubmit={handleSubmit}>
            <textarea
                ref={textareaRef}
                value={text}
                onChange={(e) => setText(e.target.value)}
                placeholder={placeholder}
                rows={2}
                onKeyDown={(e) => {
                    if (e.key === "Enter" && !e.shiftKey) {
                        e.preventDefault(); // 줄바꿈 막기
                        onSubmit(text); // 카드 추가 실행
                    }
                    if (e.key === "Escape") onCancel();
                }}
            />
            <div className="card-form-actions">
                <button
                    type="submit"
                    className="btn-save"
                    disabled={!text.trim()}
                >
                    추가
                </button>
                <button type="button" className="btn-cancel" onClick={onCancel}>
                    취소
                </button>
            </div>
        </form>
    );
}

export default CardForm;

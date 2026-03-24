import React, { useCallback, useEffect, useState, memo } from "react";

function Card({ card, onUpdate, onDragStart, onDragEnd, onDelete }) {
    const [isEditing, setIsEditing] = useState(false);
    const [editText, setEditText] = useState(card.text);

    useEffect(() => {
        setEditText(card.text);
    }, [card]);

    // card.text : 부모(App)가 들고있는 원본 데이터
    // editText : 사용자가 키보드로 치고있는 임시데이터

    const handleSave = useCallback(() => {
        const trimmed = editText.trim();

        if (trimmed && trimmed !== card.text) {
            onUpdate(card.id, trimmed);
        } else if (!trimmed) {
            setEditText(card.text);
        }
        setIsEditing(false);
        // isEditing : true (수정 모드) | false(읽기 모드)
    }, [editText, card.id, card.text, onUpdate]);

    const handleKeyDown = useCallback(
        (e) => {
            if (e.key === "Enter") {
                e.preventDefault();

                handleSave();
            }
            if (e.key === "Escape") {
                setEditText(card.text);
                setIsEditing(false);
            }
        },
        [handleSave, card.text],
    );

    const handleDoubleClick = useCallback(() => {
        setIsEditing(true);
    }, []);

    const handleDelete = useCallback(() => {
        onDelete(card.id);
    }, [onDelete, card.id]);

    const handleDragStart = useCallback(
        (e) => onDragStart(e, card),
        [onDragStart, card],
    );

    if (isEditing) {
        return (
            <div className="card card-editing">
                <textarea
                    value={editText}
                    onChange={(e) => setEditText(e.target.value)}
                    onBlur={handleSave}
                    onKeyDown={handleKeyDown}
                    autoFocus
                    rows={3}
                />
            </div>
        );
    }

    return (
        <div
            className="card"
            draggable
            onDragStart={handleDragStart}
            onDragEnd={onDragEnd}
        >
            <p className="card-text" onDoubleClick={handleDoubleClick}>
                {card.text}
            </p>
            <button
                className="card-delete"
                onClick={handleDelete}
                aria-label="삭제"
            >
                x
            </button>
        </div>
    );
}

export default memo(Card);

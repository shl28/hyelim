import React, { useState } from "react";
import Column from "./Column";

// KanbanBoard = 드래그 상태 관리 + 카드 이동 + 컬럼 연결
function KanbanBoard({
    columns,
    cards,
    onAddCard,
    onMoveCard,
    onDeleteCard,
    onUpdateCard,
}) {
    const [draggedCard, setDraggedCard] = useState(null); // 현재 드래그 중인 카드 : 어떤 카드를 옮기는 지 기억
    const [dragOverColumn, setDragOverColumn] = useState(null); // 현재 마우스가 올라가있는 컬럼 의미

    const handleDragStart = (e, card) => {
        setDraggedCard(card); // 상태 변경 함수 - 현재 이동(드래그) 중인 카드(카드 내 데이터: id, text, columnId)를 저장
        e.dataTransfer.effectAllowed = "move"; // 이동가능하게 설정
        e.dataTransfer.setData("text/plain", card.id); // cardId 따로저장
        e.target.style.opacity = "0.5"; // 카드 반투명 처리
    };
    // setData(format, data) : 택배상자에 물건을 넣는 과정
    // effectAllowed : 어떤 동작이 가능한지 설정(move, copy, link)
    // e.dataTransfer : 브라우저 표준방식(안정적)
    // draggedCard : 복잡한 객체 데이터 전체 다룰 때 card(id, content, date) 정보를 참조해서
    // 카드의 배경색 변경 등 실시간 피드백을 주기 좋음

    const handleDragEnd = (e) => {
        e.target.style.opacity = "1";
        setDraggedCard(null);
        setDragOverColumn(null);
        // UI 복구, 상태 초기화
    };

    // 드래그 중
    const handleDragOver = (e, columnId) => {
        e.preventDefault();
        e.dataTransfer.dropEffect = "move";
        setDragOverColumn(columnId);
    };

    // 컬럼을 벗어날 때
    const handleDragLeave = () => {
        setDragOverColumn(null);
    };

    const handleDrop = (e, targetColumnId) => {
        e.preventDefault();
        const cardId = e.dataTransfer.getData("text/plain"); // 카드 id 가져오기
        // getData(format) : 목적지에 도착해서 상자를 열어 물건(data)을 꺼냄

        if (cardId && draggedCard && draggedCard.columnId !== targetColumnId) {
            // 같은 컬럼인지 체크, 다를 경우 이동 실행
            onMoveCard(cardId, targetColumnId);
        }

        // 상태 초기화
        setDraggedCard(null);
        setDragOverColumn(null);
    };

    return (
        <main className="board">
            {columns.map((column) => (
                <div
                    key={column.id}
                    className={`column-wrapper ${dragOverColumn === column.id ? "drag-over" : ""}`}
                    onDragOver={(e) => handleDragOver(e, column.id)}
                    onDragLeave={handleDragLeave}
                    onDrop={(e) => handleDrop(e, column.id)}
                >
                    <Column
                        column={column}
                        cards={cards.filter((c) => c.columnId === column.id)}
                        onAddCard={onAddCard}
                        onDeleteCard={onDeleteCard}
                        onUpdateCard={onUpdateCard}
                        onDragStart={handleDragStart}
                        onDragEnd={handleDragEnd}
                        isDragging={draggedCard?.columnId === column.id}
                    />
                </div>
            ))}
        </main>
    );
}

export default KanbanBoard;

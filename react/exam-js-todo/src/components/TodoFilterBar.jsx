import React from "react";

const FILTERS = [
    // 버튼 정보로 데이터 관리
    { value: "all", label: "전체" },
    { value: "active", label: "할 일" },
    { value: "completed", label: "완료" },
];

function TodoFilterBar({ filter, onChange, activeCount, completedCount }) {
    // filter : 현재 선택된 필터
    // onChange : 필터 변경 함수
    // activeCount : 미완료 갯수
    // completedCount : 완료 갯수
    return (
        <div className="filter-bar">
            {/* 배열돌면서 버튼 (3개) 생성 (전체, 할일, 완료) */}
            {FILTERS.map(({ value, label }) => (
                <button
                    key={value}
                    type="button"
                    className={filter === value ? "filter active" : "filter"}
                    onClick={() => onChange(value)}
                >
                    {/* 클릭하여 부모로 value값 전달 */}
                    {label}
                    {value === "active" ? `(${activeCount})` : null}
                    {value === "completed" ? `(${completedCount})` : null}
                </button>
            ))}
        </div>
    );
}

export default TodoFilterBar;

// 상태 흐름
// 버튼 클릭 -> onChange value 전달 -> 부모 setFilter(value) -> filter 상태 변경
// TodoFilterBar 다시 렌더링 -> 선택된 버튼의 스타일 변경(active) -> filterTodos 실행 -> 리스트 변경

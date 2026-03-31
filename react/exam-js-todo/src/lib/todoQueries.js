import React from "react";

export function filterTodos(todos, filter) {
    switch (filter) {
        case "active":
            return todos.filter((t) => !t.done); // done false 인 값만

        case "completed":
            return todos.filter((t) => t.done); // done true 인 값만

        default:
            return todos; // all 이면 전체 반환
    }
    // filter 값에 따라 보여줄 Todo목록만 골라서 반환
    // todos: 전체 할일 목록
    // filter: 현재 필터 상태('all', 'active', 'completed')
}

export function countByDone(todos) {
    return todos.reduce(
        // reduce 개수집계나 통계 낼 때 사용하는 함수 - 배열 한번 돌면서 집계
        (acc, t) => {
            // acc: 누적값, t: 현재 todo 초기값{ active: 0, completed: 0 }
            if (t.done) acc.completed += 1;
            else acc.active += 1;
            return acc;
        },
        { active: 0, completed: 0 },
    );
}
// todos.reduce((acc, t) => {}, 초기값)

// const todos = [
//   { done: false },
//   { done: true },
//   { done: false }
// ];

// 1번째: acc { active: 1, completed: 0 }
// 2번째: acc { active: 1, completed: 1 }
// 3번째: acc { active: 2, completed: 1 }

// const active = todos.filter(t => !t.done).length;
// const completed = todos.filter(t => t.done).length;

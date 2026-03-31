import type { Todo, TodoFilter } from "../types/todo";

export function filterTodos(todos: Todo[], filter: TodoFilter) {
    switch (filter) {
        case "active":
            return todos.filter((t) => !t.done); // done false 인 값만

        case "completed":
            return todos.filter((t) => t.done); // done true 인 값만

        default:
            return todos; // all 이면 전체 반환
    }
}

export function countByDone(todos: Todo[]): {
    active: number;
    completed: number;
} {
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

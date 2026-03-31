import React, { useMemo, useState } from "react";
import TodoPageHeader from "./components/TodoPageHeader";
import TodoForm from "./components/TodoForm";
import TodoList from "./components/TodoList";
import TodoStats from "./components/TodoStats";
import TodoFilterBar from "./components/TodoFilterBar";
import { filterTodos, countByDone } from "./lib/todoQueries";

function App() {
    const [todos, setTodos] = useState([
        { id: 1, text: "JavaScript 객체로 Todo 모델", done: true },
        { id: 2, text: "Todo CRUD 완성하기", done: false },
        { id: 3, text: "filterTodos 따라가 보기", done: false },
    ]);

    const [editing, setEditing] = useState(null);
    const [filter, setFilter] = useState("all");

    const visible = useMemo(() => filterTodos(todos, filter), [todos, filter]);

    const { active, completed } = useMemo(() => countByDone(todos), [todos]);

    function nextId(list) {
        if (list.length === 0) return 1; // 목록이 비어있으면 1 반환
        return Math.max(...list.map((t) => t.id)) + 1;
        // 목록 있을경우 할일 객체 배열에서 id값 추출해서 새로운 배열 [1,2,3] 만듦
        // 최대값 3 + 1 한 4를 id로 부여
    }

    const handleCreate = (text) => {
        setTodos((prev) => [...prev, { id: nextId(prev), text, done: false }]);
    };

    const handleUpdate = (updated) => {
        // 수정된 Todo 기존 리스트에서 찾아서 교체
        setTodos((prev) =>
            prev.map((t) => (t.id === updated.id ? updated : t)),
        );
        setEditing(null);
    };
    // 0. 사용자가 수정 -> updated 객체 생성
    // 1. handleUpdate 호출
    // 2. todos 배열에서 해당 id 찾음
    // 3. 찾을 경우 해당 요소만 교체
    // 4. 화면 자동 갱신

    const handleToggle = (id) => {
        setTodos((prev) =>
            prev.map((t) => (t.id === id ? { ...t, done: !t.done } : t)),
        );
    };

    const handleDelete = (id) => {
        setTodos((prev) => prev.filter((t) => t.id !== id));
        setEditing((e) => (e?.id === id ? null : e)); // 삭제 대상이 수정 중이면 null (취소)
    };

    const handleEdit = (id) => {
        const found = todos.find((t) => t.id === id); // 수정하려는 id 찾아 found에 저장
        setEditing(found ?? null); // 수정할 대상 지정 -> ??  : found 있음-> found | 없으면 null
    };

    // 1. handleEdit -> editing 상태 변경 -> TodoForm 에서 감지(useEffect) -> input 에 기존 값 자동 입력
    //  -> 사용자가 수정 가능한 상태

    return (
        <div className="app">
            <TodoPageHeader title="Todo · JavaScript">
                <code>객체</code> 모델 · <code>useState</code> ·{" "}
                <code>filterTodos</code> / <code>countByDone</code> · TS 버전과
                동작은 같고 타입만 없습니다. 추가 컴포넌트:{" "}
                <code>TodoPageHeader.jsx</code>
            </TodoPageHeader>
            <main className="main">
                <section className="panel">
                    <h2>{editing ? "할일 수정" : "할일 추가"}</h2>
                    <TodoForm
                        editing={editing}
                        onCreate={handleCreate}
                        onUpdate={handleUpdate}
                        onCancelEdit={() => setEditing(null)}
                    />
                </section>
                <TodoStats todos={todos} />

                <section className="panel">
                    <h2>목록</h2>
                    <TodoFilterBar
                        filter={filter}
                        onChange={setFilter}
                        activeCount={active}
                        completedCount={completed}
                    />
                    <TodoList
                        todos={visible}
                        onToggle={handleToggle}
                        onEdit={handleEdit}
                        onDelete={handleDelete}
                    />
                </section>
            </main>
        </div>
    );
}

export default App;

// 전체구조
// todos 상태 (원본 데이터) -> filterTodos 보여줄 목록 필터링 -> countByone(개수 계산) - ui 렌더링

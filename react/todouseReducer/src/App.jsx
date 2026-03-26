import React, { useReducer, useRef } from "react";
import "./App.css";
import Header from "./components/Header";
import List from "./components/List";
import Editor from "./components/Editor";

const mockData = [
    {
        id: 0,
        isDone: false,
        content: "React 공부하기",
        date: new Date().getTime(),
    },
    {
        id: 1,
        isDone: false,
        content: "빨래하기",
        date: new Date().getTime(),
    },
    {
        id: 2,
        isDone: false,
        content: "노래 연습하기",
        date: new Date().getTime(),
    },
];
// 상태변화 로직
function reducer(state, action) {
    switch (action.type) {
        case "CREATE":
            return [action.newItem, ...state];

        case "UPDATE":
            return state.map((it) =>
                it.id === action.targetId ? { ...it, isDone: !it.isDone } : it,
            );

        case "DELETE":
            return state.filter((it) => it.id !== action.targetId);

        default:
            return state;
    }
}
// state : todo 배열
// action : dispatch에 보낼 명령 객체
// reducer : 현재 상태 + 명령 받아 새로운 상태(state) 반환하는 함수
// dispatch 실행되면 reducer가 호출
// type 에 따라 함수 실행

function App() {
    const [todos, dispatch] = useReducer(reducer, mockData);
    //const [state, dispatch] = useReducer(reducer, initialstate);
    // stste : 현재 상태 값(이전의 todo)
    // dispatch : 상태 변경 명령(action)을 보낼 함수
    // reducer : 상태를 실제로 변경하는 '로직함수'
    // initialstate : 초기 상태값
    // 리액트에서 간단한 상태를 useState, 상태가 많아지면 useReducer 사용

    const idRef = useRef(3); // 새로운 todo id 를 만들기 위한 값

    const onCreate = (content) => {
        dispatch({
            type: "CREATE",
            newItem: {
                id: idRef.current,
                isDone: false,
                content: content,
                date: new Date().getTime(),
            },
        });
        idRef.current += 1;
    };

    const onUpdate = (targetId) => {
        dispatch({ type: "UPDATE", targetId });
    };

    const onDelete = (targetId) => {
        dispatch({ type: "DELETE", targetId });
    };
    // 전체 동작
    // 사용자 클릭(입력) -> onCreate | onUpdate | onDelete => dispatch({type:"", ...})
    // reducer(state, action) -> 새로운 state(todo) 생성 -> 컴포넌트 자동 렌더링

    // useReducer : 복잡한 상태관리용 리액트 훅함수
    // reducer() : 상태 변경 로직을 한곳에 모은 함수
    // dispatch : 상태변경 명령(action)을 보냄

    return (
        <div className="App">
            <Header />
            <Editor onCreate={onCreate} />
            {/* App -> Editor로 함수 전달 */}
            <List todos={todos} onUpdate={onUpdate} onDelete={onDelete} />
        </div>
    );
}

export default App;

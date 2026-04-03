import React from "react";
import { useState } from "react";

function App() {
    const [todos, setTodos] = useState([]);
    const [input, setInput] = useState("");

    const onSubmit = (e) => {
        e.preventDefault();

        if (!input) return;

        setTodos((prev) => [...prev, input]);
        setInput("");
    };

    const onDelete = (index) => {
        setTodos((prev) => prev.filter((_, i) => i !== index));
    };

    return (
        <div>
            <form onSubmit={onSubmit}>
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    placeholder="할 일"
                />
                <button type="submit">추가</button>
            </form>

            {todos.map((todo, index) => (
                <ul key={index}>
                    <li>
                        {todo}
                        <button onClick={() => onDelete(index)}>삭제</button>
                    </li>
                </ul>
            ))}
        </div>
    );
}

export default App;

import React from "react";
import { useState } from "react";

function App() {
    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    const [result, setResult] = useState(null);
    const [error, setError] = useState("");

    const onSubmit = (e) => {
        e.preventDefault();

        if (!name || !age) {
            setError("값을 입력하세요");
            setResult(null);
            return;
        }

        setError("");
        setResult({ name, age });

        setName("");
        setAge("");
    };

    return (
        <div>
            <form onSubmit={onSubmit}>
                <div className="form-group">
                    <label>이름 </label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="이름"
                    />
                </div>
                <div className="form-group">
                    <label>나이 </label>
                    <input
                        type="text"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
                        placeholder="나이"
                    />
                </div>
                <button type="submit">확인</button>
            </form>

            {error && <p>{error}</p>}

            {result && (
                <div>
                    <p>이름: {result.name}</p>
                    <p>나이: {result.age}</p>
                </div>
            )}
        </div>
    );
}

export default App;

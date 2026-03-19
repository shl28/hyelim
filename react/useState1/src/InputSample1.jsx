import React from "react";
import { useState } from "react";

function InputSample1() {
    const [text, setText] = useState("");

    const onChange = (e) => {
        setText(e.target.value);
        // e.target : 이벤트를 발생하는 DOM
        // e.target.value : 현재 input 창 입력한 값
    };

    const onReset = () => {
        setText("");
    };

    return (
        <div>
            <input onChange={onChange} value={text} />
            <button onClick={onReset}>초기화</button>
            <div>
                <b>값: {text}</b>
            </div>
        </div>
    );
}

export default InputSample1;

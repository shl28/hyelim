import React from "react";
// props 객체 전체 받기

function Welcome1({ name, color }) {
    return <h1 style={{ color }}>안녕하세요. {name}</h1>;
}

export default Welcome1;

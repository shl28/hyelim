import React from "react";
import styled from "styled-components";

const Button = styled.button`
    paddingl: 10px 20px;
    border-radius: 6px;
    font-weight: 600;
    cursor: pointer;
    border: nene;
    transition: 0.2s;

    background: ${({ variant }) =>
        variant === "outline"
            ? "white"
            : variant === "danger"
              ? "#dc2626"
              : "#4f46e5"};

    color: ${({ variant }) => (variant === "outline" ? "#4f46e5" : " #fff")};
    border: ${({ variant }) =>
        variant === "outline" ? "2px solid #4f46e5" : "none"};

    &:hover {
        opacity: 0.8;
    }
`;

function App() {
    return (
        <div style={{ display: "flex", gap: "10px" }}>
            <Button>Primary</Button>
            <Button variant="outline">Outline</Button>
            <Button variant="danger">Danger</Button>
        </div>
    );
}

export default App;

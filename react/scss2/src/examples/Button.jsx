import "./Button.scss";

function Button({
    children,
    variant = "primary",
    size = "medium",
    onClick,
    disabled = false,
}) {
    return (
        <button
            className={`btn btn--${variant} btn--${size}`}
            onClick={onClick}
            disabled={disabled}
        >
            {children}
        </button>
    );
}

export default Button;

// children : 버튼안에 들어갈 내용
// variant : 버튼의 종류
// size : 버튼 크기
// onClick : 클릭 이벤트
// disabled : 비활성화

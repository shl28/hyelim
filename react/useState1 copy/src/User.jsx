import React, { useEffect } from "react";

const User = React.memo(function User({ user, onRemove, onToggle }) {
    useEffect(() => {
        console.log("user값이 설정됨");
        console.log(user);
        return () => {
            console.log("user가 바뀌기 전");
            console.log(user);
        };
    }, [user]);

    return (
        <div>
            <b
                style={{
                    cursor: "pointer",
                    color: user.active ? "green" : "black",
                }}
                onClick={() => onToggle(user.id)}
            >
                {user.username}
            </b>
            &nbsp;
            <span>({user.email})</span>
            <button onClick={() => onRemove(user.id)}>삭제</button>
        </div>
    );
});

export default User;

// useEffect(()=>{실행할 코드}, []); 렌더링이 끝난 후 실행되는 코드

// 1. useEffect(()=>{"실행 1번"}, []);  컴포넌트가 생성될 때 한번 실행
// 2. useEffect(()=>{console.log("count 변경됨")}, [count]);  count 변경 시 실행
// 3. useEffect(()=>{"실행 1번"}); 렌더링 될때마다 실행

// useEffect(() => {
//   const id = setInterval(() => {
//     console.log("실행 중");
//   }, 1000);

//   return () => {
//     clearInterval(id);
//   };
// }, []);
// return -> unmount
// 컴포넌트 사라질때나 다시 실행되기 직전에 -> 실행

// 5. 대표 예제
// 📌 1) 자동 포커스
// useEffect(() => {
//   inputRef.current.focus();
// }, []);
// 📌 2) 스크롤 이벤트
// useEffect(() => {
//   const handleScroll = () => {
//     console.log(window.scrollY);
//   };

//   window.addEventListener("scroll", handleScroll);

//   return () => {
//     window.removeEventListener("scroll", handleScroll);
//   };
// }, []);
// 2) 스크롤 이벤트
// 언마운드를 안시키면 랜더링 마다 계속 실행 이벤트가 중복 등록된다.

// 📌 3) API 호출
// useEffect(() => {
//   fetch("/api/data")
//     .then(res => res.json())
//     .then(data => setData(data));
// }, []);
// 3번 useeffect를 빼면 무한 api 호출

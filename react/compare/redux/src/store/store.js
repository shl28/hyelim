import { configureStore } from "@reduxjs/toolkit";
import counterReducer from "./counterSlice";
import userReducer from "./userSlice";

export const store = configureStore({
    reducer: {
        counter: counterReducer,
        user: userReducer,
    },
});

// 전역상태 저장소
// 하나의 전체 상태로 합치는 것

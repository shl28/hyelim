import React from "react";
import UserInfo from "./UserInfo";
export default function UserSection({ user, updateUserName, updateUserAge }) {
    return (
        <section className="section">
            <h2>👤 사용자 정보</h2>
            <UserInfo
                user={user}
                updateUserName={updateUserName}
                updateUserAge={updateUserAge}
            />
        </section>
    );
}

async function request(path, options = {}) {
    // 모든 api 요청의 공통 처리 담당
    const { json, ...init } = options;
    const headers = { ...init.headers };
    if (json !== undefined) {
        headers["Content-Type"] = "application/json";
        init.body = JSON.stringify(json);
    }
    //  아래 내용 전송됨
    // {
    //     "email": "...",
    //     "password": "..."
    // }
    const res = await fetch(path, {
        ...init,
        headers,
        credentials: "include", // 세션, 쿠키 포함하여 전송 // 앞 내용 안쓴다면 로그인 성공해도 유지 안됨
    });
    const text = await res.text(); // 먼저 text 형식으로 받고
    let data = null;
    if (text) {
        try {
            data = JSON.parse(text); // json 파일일 시 , parsing
        } catch {
            data = text;
        }
    }
    if (!res.ok) {
        // 에러 처리
        const err = new Error(
            data?.error || res.statusText || "Request failed",
        );
        err.status = res.status;
        err.data = data;
        throw err;
    }
    return data;
}

// export const api = {
//     login: (body) => request("/api/auth/login", { method: "POST", json: body }),
// };

// -----------------

// POST/api/posts 글 + 이미지
// Patch/api/posts/:id(수정+이미지)

// FromData 일때 Content-Type 직접쓰면 안됨- 브라우저가 알아서 Content-Type 만듦
async function requestForm(path, method, formData) {
    const res = await fetch(path, {
        method,
        body: formData,
        credentials: "include", // 세션, 쿠키 포함하여 전송 // 앞 내용 안쓴다면 로그인 성공해도 유지 안됨
    });
    const text = await res.text(); // 먼저 text 형식으로 받고
    let data = null;
    if (text) {
        try {
            data = JSON.parse(text); // json 파일일 시 , parsing
        } catch {
            data = text;
        }
    }
    if (!res.ok) {
        // 에러 처리
        const err = new Error(
            data?.error || res.statusText || "Request failed",
        );
        err.status = res.status;
        err.data = data;
        throw err;
    }
    return data;
}

export const api = {
    getCategories: () => request("./api/categories"),
    createPost: (formData) => requestForm("/api/posts", "POST", formData),

    login: (body) => request("/api/auth/login", { method: "POST", json: body }),
    logout: () => request("/api/auth/logout", { method: "POST" }),
    join: (body) => request("/api/auth/join", { method: "POST", json: body }),
};

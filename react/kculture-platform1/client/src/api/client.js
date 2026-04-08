async function request(path, options = {}) {
    //모든 api 요청의  공통처리담당
    const { json, ...init } = options;
    const headers = { ...init.headers };
    if (json !== undefined) {
        headers["Content-Type"] = "application/json";
        init.body = JSON.stringify(json);
    } //
    // {
    //   "email": "...",
    //   "password": "..."
    // } 내용이 전송

    const res = await fetch(path, {
        ...init,
        headers,
        credentials: "include", // 세션 쿠키 포함 - 로그인성공해도 유지안됨
    });
    const text = await res.text(); //먼저 텍스트로 받고
    let data = null;
    if (text) {
        try {
            data = JSON.parse(text); //json 파일이면 파싱
        } catch {
            data = text;
        }
    }
    if (!res.ok) {
        //에러처리
        const err = new Error(
            data?.error || res.statusText || "Request failed",
        );
        err.status = res.status;
        err.data = data;
        throw err;
    }
    return data;
}

//POST /api/posts글 + 이미지
//PATCH /api/posts/:id(수정+ 이미지)
//FormData 일때 Content-Type 직접쓰면 안됨 - 브라우저가 알아서 Content-Type 을 만듬
async function requestForm(path, method, formData) {
    //모든 api 요청의  공통처리담당

    const res = await fetch(path, {
        method,
        body: formData,
        credentials: "include", // 세션 쿠키 포함 - 로그인성공해도 유지안됨
    });
    const text = await res.text(); //먼저 텍스트로 받고
    let data = null;
    if (text) {
        try {
            data = JSON.parse(text); //json 파일이면 파싱
        } catch {
            data = text;
        }
    }
    if (!res.ok) {
        //에러처리
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
    getCategories: () => request("/api/categories"),
    getPosts: (params) => {
        //api.getPosts({categoryId:2, page:3})
        const q = new URLSearchParams(); //쿼리스트링을 쉬게 만들기 위한 js객체
        if (params?.categoryId) q.set("categoryId", String(params.categoryId)); //값이 있을때만 넣는다. q.set('categoryId','2')
        if (params?.page) q.set("page", String(params.page)); //q.set('page','3')
        if (params?.pageSize) q.set("pageSize", String(params.pageSize)); //실제 url categoryId=2&page=3
        const s = q.toString();
        return request(`/api/posts${s ? `?${s}` : ""}`); // /api/posts?categoryId=2&page=3
    },

    //post 새 글  multipart Formdata(이미지 필드 포함)
    createPost: (formData) => requestForm("/api/posts", "POST", formData), //게시글

    login: (body) => request("/api/auth/login", { method: "POST", json: body }),
    logout: () => request("/api/auth/logout", { method: "POST" }),
    join: (body) => request("/api/auth/join", { method: "POST", json: body }),
};

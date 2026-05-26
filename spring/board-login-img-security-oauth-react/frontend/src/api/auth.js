import client from "./client";

export const authApi = {
    signup: (data) => client.post("/members", data),

    login: (username, password) =>
        client.post("/auth/login", { username, password }),

    me: () => client.get("/auth/me"),

    logout: () => client.post("/auth/logout"),
};

import client from "./client";

export const postsApi = {
    findAll: () => client.get("/posts"),

    findById: (id) => client.get(`/posts/${id}`),

    create: (request, files) => {
        const formData = new FormData();

        formData.append(
            "request",
            new Blob([JSON.stringify(request)], { type: "application/json" }),
        );

        if (files && files.length > 0) {
            files.forEach((file) => {
                formData.append("files", file);
            });
        }

        return client.post("/posts", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });
    },

    update: (id, data) => client.put(`/posts/${id}`, data),

    delete: (id) => client.delete(`/posts/${id}`),
};

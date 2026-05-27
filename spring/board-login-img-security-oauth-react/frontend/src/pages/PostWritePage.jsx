import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { postsApi } from "../api/posts";

export default function PostWritePage() {
    const navigate = useNavigate();

    const [form, setForm] = useState({ title: "", content: "" });
    const [files, setFiles] = useState([]);
    const [previews, setPreviews] = useState([]);
    const [error, setError] = useState("");
    const [submitting, setSubmitting] = useState(false);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleFileChange = (e) => {
        const selected = Array.from(e.target.files);

        if (selected.length > 3) {
            setError("이미지는 최대 3장까지 업로드 가능합니다.");
            e.target.value = "";
            return;
        }

        const allowedExts = ["jpg", "jpeg", "png"];
        const invalidFile = selected.find((f) => {
            const ext = f.name.split(".").pop().toLowerCase();
            return !allowedExts.includes(ext);
        });
        if (invalidFile) {
            setError(`허용되지 않은 확장자입니다: ${invalidFile.name}`);
            e.target.value = "";
            return;
        }

        const tooBigFile = selected.find((f) => f.size > 10 * 1024 * 1024);
        if (tooBigFile) {
            setError(`파일 크기는 10MB 이하여야 합니다: ${tooBigFile.name}`);
            e.target.value = "";
            return;
        }

        setError("");
        setFiles(selected);

        const urls = selected.map((file) => URL.createObjectURL(file));
        setPreviews(urls);
    };

    const removeFile = (index) => {
        const newFiles = files.filter((_, i) => i !== index);
        const newPreviews = previews.filter((_, i) => i !== index);
        setFiles(newFiles);
        setPreviews(newPreviews);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSubmitting(true);

        try {
            const res = await postsApi.create(form, files);
            navigate(`/posts/${res.data.id}`);
        } catch (err) {
            setError(err.response?.data?.message || "글 등록에 실패했습니다.");
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="max-w-3xl mx-auto">
            <h1 className="text-2xl font-bold mb-6">글쓰기</h1>

            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        제목
                    </label>
                    <input
                        type="text"
                        name="title"
                        value={form.title}
                        onChange={handleChange}
                        required
                        maxLength={200}
                        className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        내용
                    </label>
                    <textarea
                        name="content"
                        value={form.content}
                        onChange={handleChange}
                        required
                        rows={10}
                        className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        이미지 (최대 3장, jpg/png, 파일당 10MB)
                    </label>
                    <input
                        type="file"
                        multiple
                        accept="image/jpeg, image/jpg, image/png"
                        onChange={handleFileChange}
                        className="w-full text-sm text-gray-600
                    file:mr-4 file:py-2 file:px-4
                    file:rounded file:border-0
                    file:text-sm file:font-medium
                    file:bg-blue-50 file:text-blue-700
                    hover:file:bg-blue-100
                    "
                    />
                </div>

                {previews.length > 0 && (
                    <div className="grid grid-cols-3 gap-3">
                        {previews.map((url, idx) => (
                            <div key={idx} className="relative">
                                <img
                                    src={url}
                                    alt={`미리보기 ${idx + 1}`}
                                    className="w-full h-32 object-cover rounded border border-gray-200"
                                />
                                <button
                                    type="button"
                                    onClick={() => removeFile(idx)}
                                    className="absolute top-1 right-1 w-6 h-6 bg-black/60 text-white rounded-full text-xs hover:bg-black/80"
                                >
                                    ✕
                                </button>
                                <p className="mt-1 text-xs text-gray-500 truncate">
                                    {files[idx]?.name}
                                </p>
                            </div>
                        ))}
                    </div>
                )}

                {error && (
                    <div className="p-3 bg-red-50 border border-red-200 text-red-700 rounded text-sm">
                        {error}
                    </div>
                )}

                <div className="flex gap-2 justify-end pt-4">
                    <button
                        type="button"
                        onClick={() => navigate("/posts")}
                        className="px-4 py-2 bg-gray-100 hover:bg-gray-200 rounded"
                    >
                        취소
                    </button>
                    <button
                        type="submit"
                        disabled={submitting}
                        className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:bg-gray-400"
                    >
                        {submitting ? "등록중..." : "등록"}
                    </button>
                </div>
            </form>
        </div>
    );
}

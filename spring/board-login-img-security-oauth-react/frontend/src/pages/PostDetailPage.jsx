import { useEffect, useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { postsApi } from "../api/posts";
import { useAuth } from "../context/AuthContext";

export default function PostDetailPage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const { user } = useAuth();

    const [post, setPost] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    // 수정 모드 관련
    const [editing, setEditing] = useState(false);
    const [editForm, setEditForm] = useState({ title: "", content: "" });
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        postsApi
            .findById(id)
            .then((res) => {
                setPost(res.data);
                setEditForm({
                    title: res.data.title,
                    content: res.data.content,
                });
            })
            .catch((err) =>
                setError(
                    err.response?.data?.message ||
                        "게시글을 불러올 수 없습니다.",
                ),
            )
            .finally(() => setLoading(false));
    }, [id]);

    const isOwner = user && post && user.id === post.memberId;

    const handleDelete = async () => {
        if (!window.confirm("정말 삭제하시겠습니까?")) return;
        try {
            await postsApi.delete(id);
            navigate("/posts");
        } catch (err) {
            alert(err.response?.data?.message || "삭제 실패");
        }
    };

    const handleUpdate = async (e) => {
        e.preventDefault();
        setSubmitting(true);
        try {
            const res = await postsApi.update(id, editForm);
            setPost(res.data);
            setEditing(false);
        } catch (err) {
            alert(err.response?.data?.message || "수정 실패");
        } finally {
            setSubmitting(false);
        }
    };

    if (loading)
        return (
            <div className="text-center py-12 text-gray-500">로딩 중...</div>
        );

    if (error) {
        return (
            <div className="max-w-2xl mx-auto">
                <div className="p-4 bg-red-50 border border-red-200 text-red-700 rounded">
                    {error}
                </div>
                <Link
                    to="/posts"
                    className="inline-block mt-4 text-blue-600 hover:underline"
                >
                    ← 목록으로
                </Link>
            </div>
        );
    }

    if (!post) return null;

    // ========== 수정 모드 ==========
    if (editing) {
        return (
            <div className="max-w-3xl mx-auto">
                <h1 className="text-2xl font-bold mb-6">글 수정</h1>
                <form onSubmit={handleUpdate} className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            제목
                        </label>
                        <input
                            type="text"
                            value={editForm.title}
                            onChange={(e) =>
                                setEditForm({
                                    ...editForm,
                                    title: e.target.value,
                                })
                            }
                            required
                            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            내용
                        </label>
                        <textarea
                            value={editForm.content}
                            onChange={(e) =>
                                setEditForm({
                                    ...editForm,
                                    content: e.target.value,
                                })
                            }
                            required
                            rows={10}
                            className="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
                        />
                    </div>
                    <div className="flex gap-2 justify-end">
                        <button
                            type="button"
                            onClick={() => setEditing(false)}
                            className="px-4 py-2 bg-gray-100 hover:bg-gray-200 rounded"
                        >
                            취소
                        </button>
                        <button
                            type="submit"
                            disabled={submitting}
                            className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:bg-gray-400"
                        >
                            {submitting ? "저장 중..." : "저장"}
                        </button>
                    </div>
                </form>
            </div>
        );
    }

    // ========== 일반 보기 모드 ==========
    return (
        <div className="max-w-3xl mx-auto">
            <Link
                to="/posts"
                className="inline-block mb-4 text-blue-600 hover:underline text-sm"
            >
                ← 목록으로
            </Link>

            <article className="bg-white border border-gray-200 rounded-lg p-6">
                <header className="border-b border-gray-200 pb-4 mb-4">
                    <h1 className="text-2xl font-bold text-gray-900 mb-2">
                        {post.title}
                    </h1>
                    <div className="flex items-center justify-between text-sm text-gray-600">
                        <span>작성자: {post.memberNickname}</span>
                        {isOwner && (
                            <div className="flex gap-2">
                                <button
                                    onClick={() => setEditing(true)}
                                    className="px-3 py-1 text-sm bg-gray-100 hover:bg-gray-200 rounded"
                                >
                                    수정
                                </button>
                                <button
                                    onClick={handleDelete}
                                    className="px-3 py-1 text-sm bg-red-50 text-red-700 hover:bg-red-100 rounded"
                                >
                                    삭제
                                </button>
                            </div>
                        )}
                    </div>
                </header>

                {/* 이미지 갤러리 */}
                {post.images && post.images.length > 0 && (
                    <div className="mb-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3">
                        {post.images.map((img) => (
                            <a
                                key={img.id}
                                href={img.url}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="block"
                            >
                                <img
                                    src={img.thumbnailUrl}
                                    alt={img.originalName}
                                    className="w-full h-48 object-cover rounded border border-gray-200 hover:opacity-90"
                                />
                            </a>
                        ))}
                    </div>
                )}

                {/* 본문 */}
                <div className="whitespace-pre-wrap text-gray-800 leading-relaxed">
                    {post.content}
                </div>
            </article>
        </div>
    );
}

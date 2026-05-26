import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { postsApi } from "../api/posts";

export default function PostListPage() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        postsApi
            .findAll()
            .then((res) => setPosts(res.data))
            .catch((err) =>
                setError(err.response?.data?.message || "목록 조회 실패"),
            )
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return (
            <div className="text-center py-12 text-gray-500">로딩 중...</div>
        );
    }

    if (error) {
        return (
            <div className="max-w-2xl mx-auto p-4 bg-red-50 border border-red-200 text-red-700 rounded">
                {error}
            </div>
        );
    }

    return (
        <div className="max-w-4xl mx-auto">
            <div className="flex justify-between items-center mb-6">
                <h1 className="text-2xl font-bold">게시글 목록</h1>
                <Link
                    to="/posts/write"
                    className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                >
                    글쓰기
                </Link>
            </div>

            {posts.length === 0 ? (
                <div className="text-center py-12 text-gray-500">
                    아직 게시글이 없습니다.
                </div>
            ) : (
                <div className="grid gap-4">
                    {posts.map((post) => (
                        <Link
                            key={post.id}
                            to={`/posts/${post.id}`}
                            className="block p-5 bg-white border border-gray-200 rounded-lg hover:shadow-md transition"
                        >
                            <div className="flex gap-4">
                                {post.images && post.images.length > 0 && (
                                    <img
                                        src={post.images[0].thumbnailUrl}
                                        alt=""
                                        className="w-24 h-24 object-cover rounded flex-shrink-0"
                                    />
                                )}

                                <div className="flex-1 min-w-0">
                                    <h2 className="text-lg font-semibold text-gray-900 truncate">
                                        {post.title}
                                    </h2>
                                    <p className="mt-1 text-sm text-gray-600 line-clamp-2">
                                        {post.content}
                                    </p>
                                    <div className="mt-2 flex items-center gap-2 text-xs text-gray-500">
                                        <span>{post.memberNickname}</span>
                                        {post.images &&
                                            post.images.length > 0 && (
                                                <>
                                                    <span>·</span>
                                                    <span>
                                                        📷 {post.images.length}
                                                        장
                                                    </span>
                                                </>
                                            )}
                                    </div>
                                </div>
                            </div>
                        </Link>
                    ))}
                </div>
            )}
        </div>
    );
}

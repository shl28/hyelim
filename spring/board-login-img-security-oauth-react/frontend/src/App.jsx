import { Routes, Route, Link } from "react-router-dom";
import { useAuth } from "./context/AuthContext";
import ProtectedRoute from "./components/ProtectedRoute";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import PostListPage from "./pages/PostListPage";

function PostDetailPage() {
    return <div>게시글 상세 (작성 예정)</div>;
}
function PostWritePage() {
    return <div>글쓰기 (작성 예정)</div>;
}

function Header() {
    const { user, logout } = useAuth();

    return (
        <header className="flex justify-between items-center px-8 py-4 border-b border-gray-200 bg-white">
            <Link
                to="/"
                className="text-xl font-bold text-gray-900 no-underline"
            >
                Board App
            </Link>
            <nav className="flex gap-4 items-center">
                <Link to="/posts" className="text-blue-600 hover:underline">
                    게시판
                </Link>
                {user ? (
                    <>
                        <span className="text-gray-700">{user.nickname}님</span>
                        <Link
                            to="/posts/write"
                            className="text-blue-600 hover:underline"
                        >
                            글쓰기
                        </Link>
                        <button
                            onClick={logout}
                            className="px-3 py-1 bg-gray-100 hover:bg-gray-200 rounded text-sm"
                        >
                            로그아웃
                        </button>
                    </>
                ) : (
                    <Link to="/login" className="text-blue-600 hover:underline">
                        로그인
                    </Link>
                )}
            </nav>
        </header>
    );
}

function App() {
    return (
        <>
            <Header />
            <main style={{ padding: "2rem" }}>
                <Routes>
                    <Route path="/" element={<PostListPage />} />
                    <Route path="/posts" element={<PostListPage />} />
                    <Route path="/posts/:id" element={<PostDetailPage />} />
                    <Route
                        path="/posts/write"
                        element={
                            <ProtectedRoute>
                                <PostWritePage />
                            </ProtectedRoute>
                        }
                    />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/signup" element={<SignupPage />} />
                </Routes>
            </main>
        </>
    );
}

export default App;

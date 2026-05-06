import { useState, useEffect } from "react";
import {
    Route,
    Routes,
    useLocation,
    Link,
    useNavigate,
} from "react-router-dom";

const API = "/api/doits";
// 기본 경로

// fetchJson : API 요청 전담 함수
async function fetchJson(url, options) {
    const res = await fetch(url, {
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        ...options,
    });
    // ...options : 호출 시 추가로 옵션 부여할 예정
    if (!res.ok) {
        const text = await res.text();
        throw new Error(text || res.statusText);
    }
    if (res.status === 204) return null;
    return res.json();
}

function ListPage() {
    const location = useLocation(); // url 정보 가져옴
    const [items, setItems] = useState([]); // 게시글 목록 저장
    const [error, setError] = useState(null); // API 호출 시 에러메세지
    const [loading, setLoading] = useState(true); // 목록을 가지고 오는 중인지 여부

    useEffect(() => {
        setLoading(true);
        setError(null);
        fetchJson(API)
            .then(setItems)
            .catch((e) => setError(e.message))
            .finally(() => setLoading(false));
    }, [location.key]); // 페이지 이동시 호출

    if (loading) return <p className="text-muted">불러오는 중…</p>;
    if (error) return <div className="alert alert-danger">{error}</div>;

    return (
        <>
            <h1 className="h3 mb-3">게시판</h1>
            <table className="table table-bordered bg-white">
                <thead>
                    <tr>
                        <th>Num</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>액션</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((row) => (
                        <tr key={row.num}>
                            <td>{row.num}</td>
                            <td>
                                <Link to={`/list/${row.num}`}>{row.title}</Link>
                            </td>
                            <td>{row.content}</td>
                            <td>
                                <Link
                                    className="btn btn-sm btn-outline-primary me-1"
                                    to={`/list/${row.num}/edit`}
                                >
                                    수정
                                </Link>
                                <DeleteButton num={row.num} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {items.length === 0 && (
                <p className="text-muted">데이터가 없습니다.</p>
            )}
        </>
    );
}

function DeleteButton({ num }) {
    const navigate = useNavigate();
    const onDelete = async () => {
        if (!confirm("삭제할까요?")) return;
        try {
            const res = await fetch(`${API}/${num}`, { method: "DELETE" });
            if (res.status === 204) {
                navigate("/list", {
                    replace: true,
                    state: { msg: "삭제가 완료되었습니다." },
                });
            } else {
                alert("삭제 실패");
            }
        } catch (e) {
            alert(e.message);
        }
    };
    return (
        <button
            type="button"
            className="btn btn-sm btn-outline-danger"
            onClick={onDelete}
        >
            삭제
        </button>
    );
}

function Layout({ children }) {
    const location = useLocation();
    const flash = location.state?.msg;

    return (
        <>
            <nav className="app-nav py-3 mb-4">
                <div className="container d-flex gap-3 align-items-center">
                    <Link
                        className="navbar-brand mb-0 text-decoration-none fw-bold"
                        to="/list"
                    >
                        crud2-Api
                    </Link>
                    <Link className="btn btn-sm btn-outline-primary" to="/list">
                        목록
                    </Link>
                    <Link className="btn btn-sm btn-primary" to="/mains/add">
                        글쓰기
                    </Link>
                </div>
            </nav>
            {flash && (
                <div className="container flash-msg">
                    <div
                        className="alert alert-primary alert-dismissible fade show"
                        role="alert"
                    >
                        {flash}
                        <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="alert"
                            aria-label="Close"
                        />
                    </div>
                </div>
            )}
            <div className="container pb-5">{children}</div>
        </>
    );
}

export default function App() {
    return (
        <Layout>
            <Routes>
                <Route path="/" element={<ListPage />} />
                <Route path="/list" element={<ListPage />} />
            </Routes>
        </Layout>
    );
}

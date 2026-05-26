import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { authApi } from "../api/auth";

export default function SignupPage() {
    const [form, setForm] = useState({
        username: "",
        password: "",
        nickname: "",
    });

    const [error, setError] = useState("");
    const [submitting, setSubmitting] = useState(false);

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSubmitting(true);

        try {
            await authApi.signup(form);
            alert("회원가입이 완료되었습니다. 로그인 해주세요.");
            navigate("/login");
        } catch (err) {
            const msg =
                err.response?.data?.message || "회원가입에 실패했습니다.";
            setError(msg);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="max-w-md mx-auto mt-12 p-8 bg-white border border-gray-200 rounded-lg shadow">
            <h1 className="text-2xl font-bold mb-6 text-center">회원가입</h1>

            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        아이디(4자 이상)
                    </label>
                    <input
                        type="text"
                        name="username"
                        value={form.username}
                        onChange={handleChange}
                        required
                        minLength={4}
                        className="w-full px-3 py-2 border border-gray-400 rounded focus:outline-none focus:ring-blue-500"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        비밀번호(4자 이상)
                    </label>
                    <input
                        type="password"
                        name="password"
                        value={form.password}
                        onChange={handleChange}
                        required
                        minLength={4}
                        className="w-full px-3 py-2 border border-gray-400 rounded focus:outline-none focus:ring-blue-500"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                        닉네임
                    </label>
                    <input
                        type="text"
                        name="nickname"
                        value={form.nickname}
                        onChange={handleChange}
                        required
                        minLength={4}
                        className="w-full px-3 py-2 border border-gray-400 rounded focus:outline-none focus:ring-blue-500"
                    />
                </div>

                {error && (
                    <div className="p-3 bg-red-50 border border-red-200 text-red-700 rounded text-sm">
                        {error}
                    </div>
                )}

                <button
                    type="submit"
                    disabled={submitting}
                    className="w-full py-2 bg-blue-600 text-white rounded hover:bg-blue-700 disabled:bg-gray-400"
                >
                    {submitting ? "가입 중..." : "회원가입"}
                </button>
            </form>

            <p className="mt-4 text-center text-sm text-gray-600">
                이미 계정이 있으신가요?{" "}
                <Link to="/login" className="text-blue-600 hover:underline">
                    로그인
                </Link>
            </p>
        </div>
    );
}

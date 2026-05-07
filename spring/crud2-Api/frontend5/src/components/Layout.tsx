import { Link, useLocation } from "react-router-dom";
import type { ReactNode } from "react";

interface LayoutProps {
    children: ReactNode;
}

interface LocationState {
    msg?: string;
}

export default function Layout({ children }: LayoutProps) {
    const location = useLocation();
    const state = location.state as LocationState | null;
    const flash = state?.msg;
    return (
        <>
            <nav className="app-nav py-3 mb-4">
                <div className="container d-flex gap-3 align-items-center">
                    <Link
                        className="navbar-brand mb-0 text-decoration-none fw-bold"
                        to="/list"
                    >
                        crud2Api (frontend5)
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

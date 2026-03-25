import React, { useState } from "react";
import { FiX, FiMenu } from "react-icons/fi";
import { NavLink } from "react-router-dom";
import "./navbar.css";

function Navbar() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    //네비게이션 항목 목록
    const navItems = [
        { to: "/", label: "Home" },
        { to: "/about", label: "About" },
        { to: "/skills", label: "Skills" },
        { to: "/projects", label: "Projects" },
        { to: "/contact", label: "Contact" },
    ];

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <div className="navbar-content">
                    <div className="navbar-logo">
                        <span className="logo-text">Portfolio</span>
                    </div>
                    {/* pc용 네비게이션 */}
                    <div className="desktop-nav">
                        {navItems.map((item) => (
                            <NavLink
                                key={item.to}
                                to={item.to}
                                onClick={() => setIsMenuOpen(false)}
                                className={({ isActive }) =>
                                    `nav-button ${isActive ? "active" : ""}`
                                }
                                // isActive == true 현재 페이지와 같으면 'active' 클래스 추가
                            >
                                {item.label}
                            </NavLink>
                        ))}
                        {/* isActive : react-router-dom 에서 부여 
                    현재 주소창 (/about) 과 to속성에 적어준 경로 (to="/about")가 서로 일치하면
                    리액트 라우터가 isActive를 true로 변경, 일치하지 않으면 false  
                    <Link> 단순 이동 <NavLink> 현재 메뉴 표시하는 동적 스타일링 가능(자동감지)
                    */}
                    </div>
                    {/* 모바일용 메뉴 버튼 */}
                    <button
                        onClick={() => setIsMenuOpen(!isMenuOpen)}
                        className="mobile-menu-button"
                        aria-label="Toggle menu"
                    >
                        {isMenuOpen ? <FiX size={24} /> : <FiMenu size={24} />}
                    </button>
                </div>

                {/* 모바일 네비게이션 */}
                {isMenuOpen && (
                    <div className="mobile-nav">
                        <div className="mobile-nav-items">
                            {navItems.map((item) => (
                                <NavLink
                                    key={item.to}
                                    to={item.to}
                                    onClick={() => setIsMenuOpen(false)}
                                    className={({ isActive }) =>
                                        `mobile-nav-button ${isActive ? "active" : ""}`
                                    }
                                    // isActive == true 현재 페이지와 같으면 'active' 클래스 추가
                                >
                                    {item.label}
                                </NavLink>
                            ))}
                            {/* isActive : react-router-dom 에서 부여 
                    현재 주소창 (/about) 과 to속성에 적어준 경로 (to="/about")가 서로 일치하면
                    리액트 라우터가 isActive를 true로 변경, 일치하지 않으면 false  
                    <Link> 단순 이동 <NavLink> 현재 메뉴 표시하는 동적 스타일링 가능(자동감지)
                    */}
                        </div>
                    </div>
                )}
            </div>
        </nav>
    );
}

export default Navbar;

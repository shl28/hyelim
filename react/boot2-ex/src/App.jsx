import React from "react";
import { Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import Services from "./components/Services";
// import Contact from "./components/Contact";
import Footer from "./components/Footer";
import "./App.css";

function App() {
    return (
        <div className="app">
            <Navbar />
            <main className="main-content">
                <Routes>
                    <Route path="/" element={<Hero />} />
                    <Route path="/services" element={<Services />} />
                    {/* <Route path="/contact" element={<Contact />} /> */}
                </Routes>
            </main>
            <Footer />
        </div>
    );
}

export default App;

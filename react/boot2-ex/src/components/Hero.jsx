import React from "react";
import ImageSlider from "./slider/ImageSlider";
import HeroCard from "./HeroCard";
import "./Hero.css";
import Introsection from "./Introsection";

function Hero() {
    return (
        <section className="hero">
            <ImageSlider />
            <HeroCard />
            <Introsection />
        </section>
    );
}

export default Hero;

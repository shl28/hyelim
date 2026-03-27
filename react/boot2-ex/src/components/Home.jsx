import React from "react";
import ImageSlider from "./slider/ImageSlider";
import HomeCard from "./HomeCard";
import "./Home.css";
import Introsection from "./Introsection";

function Home() {
  return (
    <section className="home">
      <ImageSlider />
      <HomeCard />
      <Introsection />
    </section>
  );
}

export default Home;

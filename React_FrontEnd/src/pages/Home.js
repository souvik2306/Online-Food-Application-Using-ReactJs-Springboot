import React from "react";
import { Link } from "react-router-dom";
import BannerImage from "../assets/homepage5.jpg";
import Footer from "./Footer";
import Navbar from "./Navbar";
import "../styles/Home.css";

function Home() {
  return (
    <div>
      {" "}
      <Navbar />
      <div className="home" style={{ backgroundImage: `url(${BannerImage})` }}>
        <div className="headerContainer">
          <h2>FOOD-ZODIAC </h2>
          <br />
          <p> Ready To Serve You At Any Hour</p>
          <Link to="/menu">
            <button> ORDER NOW </button>
          </Link>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Home;

import React from "react";
import "../styles/About.css";
import Footer from "./Footer";
import Navbar from "./Navbar";
function About() {
  return (
    <div>
      <Navbar />
      <div className="about">
        <div className="aboutBottom">
          <br />
          <h1
            style={{
              fontFamily: `Lucida Handwriting`,
              paddingTop: `15px`,
            }}
          >
            {" "}
            ABOUT US
          </h1>
          <br />
          <br />
          <p
            style={{
              fontFamily: `Comic Sans MS`,
              color: "#000059",
              fontSize: "25px",
            }}
          >
            <b>
              Launched in 2022, Our technology platform connects customers,
              restaurant partners and delivery partners, serving their multiple
              needs. Customers use our platform to search and discover
              restaurants, read and write customer generated reviews and view
              and upload photos, order food delivery, book a table and make
              payments while dining-out at restaurants.
              <br /> <br />
              On the other hand, we provide restaurant partners with
              industry-specific marketing tools which enable them to engage and
              acquire customers to grow their business while also providing a
              reliable and efficient last mile delivery service. <br />
              <br />
              We also operate a one-stop procurement solution, Hyperpure, which
              supplies high quality ingredients and kitchen products to
              restaurant partners. We also provide our delivery partners with
              transparent and flexible earning opportunities.
            </b>
          </p>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default About;

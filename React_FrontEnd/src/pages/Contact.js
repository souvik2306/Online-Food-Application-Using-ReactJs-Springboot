import React from "react";
import "../styles/Contact.css";
import Footer from "./Footer";
import Navbar from "./Navbar";

function Contact() {
  return (
    <div>
      <Navbar />
      <div className="contact">
        <div className="contactBottom">
          <br /><br/><br/><br/><br/>
          <h1
            style={{
              fontFamily: `Lucida Handwriting`,
              paddingTop: `15px`,
            }}
          >
            {" "}
            CONTACT US
          </h1>
          <br />
          <br />
          <center
            style={{
              fontFamily: `Comic Sans MS`,
              color: "#edfcbb",
              fontSize: "35px",
            }}
          >
            <p className="mb-4">
              <font face="Gabriola" size="8">
                <b>We're open for any suggestion or just to have a chat</b>
              </font>
            </p>

            <p>
              <b>Address:</b>&nbsp;111/b Park-Street Kolkata-700006
            </p>

            <p>
              <font color="#edfcbb">
                {" "}
                <b>Phone:</b>&nbsp;+91 9876525632
              </font>
            </p>

            <p>
              <font color="#edfcbb">
                {" "}
                <b>Email:</b>&nbsp;foodzodiac@gmail.com
              </font>
            </p>

            <p>
              <font color="#edfcbb">
                {" "}
                <b>Website:</b>&nbsp;foodzodiac.com
              </font>
            </p>
          </center>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default Contact;

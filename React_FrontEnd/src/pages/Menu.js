import React, { useEffect, useState } from "react";
import { MenuList } from "../helpers/MenuList";
import MenuItem from "../components/MenuItem";
import "../styles/Menu.css";
import service from "../Services/service";
import Navbar from "./Navbar";
import Footer from "./Footer";
import { useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
function Menu() {
  const [cust_id, setCust_id] = useState("");
  const [cust_name, setCust_name] = useState("");

  const hs = useHistory();
  useEffect(() => {
    service
      .islogged()
      .then((resp) => {
        if (resp.data.cust_name) {
          console.log(resp.data);
          setCust_name(resp.data.cust_name);
          setCust_id(resp.data.cust_id);
        } else {
          window.alert("please login to continue...");
          hs.push("/login");
        }
      })
      .catch((err) => {
        console.log("Issues", err);
      });
  });
  const logoutfun = () => {
    service.logout();
    hs.push("/login");
  };
  return (
    <div className="menu">
      <Navbar />
      <br />
      <h4>Hello {cust_name}</h4>
      <div>
        <Link to={`/cust/edit/${cust_id}`}>
          <button
            className="btn btn-info"
            style={{ width: "150px", float: "right" }}
          >
            Update Profile
          </button>
        </Link>
        <button
          className="btn btn-primary"
          style={{ width: "150px", float: "right" }}
        >
          Checkout
        </button>
        <button
          className="btn btn-danger"
          style={{ width: "150px", float: "right" }}
          onClick={logoutfun}
        >
          Logout
        </button>
      </div>
      <h2 className="menuTitle">FOOD-ZODIAC PRESENTS YOU...</h2>
      <br />
      <div className="menuList">
        {MenuList.map((menuItem, key) => {
          return (
            <MenuItem
              key={key}
              image={menuItem.image}
              name={menuItem.name}
              price={menuItem.price}
            />
          );
        })}
      </div>
      <Footer />
    </div>
  );
}

export default Menu;

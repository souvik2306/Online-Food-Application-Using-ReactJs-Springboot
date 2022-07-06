import React from "react";
import "bootstrap/dist/css/bootstrap.css";
function MenuItem({ image, name, price }) {
  return (
    <div className="menuItem">
      <div style={{ backgroundImage: `url(${image})` }}> </div>

      <p>
        <h2 style={{ paddingTop: `10px`, paddingBottom: `10px` }}>{name} </h2>
        Rs.{price}/-{" "}
        <button
          style={{ float: `right`, marginRight: `10px`, width: `auto` }}
          className="btn btn-warning"
        >
          ORDER
        </button>{" "}
      </p>
    </div>
  );
}

export default MenuItem;

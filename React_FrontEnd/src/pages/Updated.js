import React from "react";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import Service from "../Services/service";

const Updated = () => {
  const [cust_name, setCust_name] = useState("");
  const [cust_email, setCust_email] = useState("");
  const [password, setCust_pass] = useState("");
  const history = useHistory();
  const { cust_id } = useParams();

  const saveCustomer = (e) => {
    e.preventDefault();

    const customer = { cust_name, cust_email, password, cust_id };

    if (cust_id) {
      //update
      console.log(customer);
      Service.update(customer)
        .then((response) => {
          console.log("Your data updated successfully", response.data);
          history.push("/menu");
        })
        .catch((error) => {
          console.log("Something went wrong", error);
        });
    } else {
      window.alert("Not Logged In");
      history.push("/login");
    }
  };
  useEffect(() => {
    if (cust_id) {
      Service.getbyid(cust_id)
        .then((customer) => {
          console.log(customer);
          setCust_name(customer.data.cust_name);
          setCust_email(customer.data.cust_email);
          setCust_pass(customer.data.password);
        })
        .catch((error) => {
          console.log("Something went wrong", error);
        });
    }
  }, []);
  return (
    <div className="container">
      <h3>Update Profile</h3>
      <hr />
      <form>
        <div className="form-group">
          <label>Enter Name</label>&emsp;&emsp;
          <br />
          <input
            type="text"
            className="form-control col-4"
            id="name"
            value={cust_name}
            onChange={(e) => setCust_name(e.target.value)}
            placeholder="Enter name"
          />
        </div>
        <div className="form-group">
          <label>Enter Email</label>&emsp;&emsp;
          <br />
          <input
            type="email"
            className="form-control col-4"
            id="department"
            value={cust_email}
            onChange={(e) => setCust_email(e.target.value)}
            placeholder="Enter email"
          />
        </div>
        <div className="form-group">
          <label>Enter Password</label>&emsp;&emsp;
          <br />
          <input
            type="text"
            className="form-control col-4"
            id="location"
            value={password}
            onChange={(e) => setCust_pass(e.target.value)}
            placeholder="Enter Password"
          />
        </div>
        <div>
          <button onClick={(e) => saveCustomer(e)} className="btn btn-success">
            Save
          </button>
        </div>
      </form>
    </div>
  );
};
export default Updated;

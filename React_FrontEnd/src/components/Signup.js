import { useState } from "react";
import "./signup.css";
import FormInput from "./FormInput";
import Navbar from "../pages/Navbar";
import Footer from "../pages/Footer";
import { useParams } from "react-router-dom";
import Service from "../Services/service";
import { useHistory } from "react-router-dom";

const Signup = () => {
  const {id} = useParams();
  const navigate = useHistory();


  const [values, setValues] = useState({
    cust_name: "",
    cust_email: "",
    //birthday: "",
    password: "",
    //confirmPassword: "",
  });

  const inputs = [
    {
      id: 1,
      name: "cust_name",
      type: "text",
      placeholder: "Enter your Name",
      errorMessage:
        "Username should be 3-16 characters and shouldn't include any special character!",
      label: "Name",
      pattern: "^[A-Za-z0-9 ]{3,16}$",
      required: true,
    },
    {
      id: 2,
      name: "cust_email",
      type: "email",
      placeholder: "Enter your Email",
      errorMessage: "It should be a valid email address!",
      label: "Email",
      required: true,
    },
    {
      id: 3,
      name: "password",
      type: "password",
      placeholder: "Enter a Suitable Password",
      errorMessage:
        "Password should be 8-20 characters and include at least 1 letter, 1 number and 1 special character!",
      label: "Password",
      pattern: `^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$`,
      required: true,
    },
  ];

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const addCust=(ev)=>
  {
    let cust_id=id;
    let cust_name = values.cust_name;
    let cust_email = values.cust_email;
    let password = values.password;
    const customer = {cust_id, cust_name, cust_email, password}
    console.log("Requested Signup",customer);
    Service.getmail(cust_email).then((resp) =>{
    console.log(resp.data, resp.data.cust_email);
    if (resp.data.cust_email == cust_email)
    {
      window.alert("Email id already exists !");
    }
    else{
    Service.create(customer).then((response) => {
      console.log("User Added Successfully",response.data);
      window.alert("User Added. You can login now");
      navigate.push("/login");
    }).catch((error) => {
      console.log("Some issues",error);
      
    })}
  }).catch((err) => {
     console.log("Issues", err);
  });
  }

  return (
    <div>
      <div>
        <Navbar />
      </div>
      <div className="signup">
        <form className="form1" onSubmit={handleSubmit}>
          <h1>Sign-Up</h1>
          {inputs.map((input) => (
            <FormInput
              key={input.id}
              {...input}
              value={values[input.name]}
              onChange={onChange}
            />
          ))}
          <center>
            {" "}
            <button className="btn btn-danger" onClick={(e) => addCust(e)}>Submit</button>
          </center>
        </form>
      </div>
      <Footer/>
    </div>
  );
};

export default Signup;

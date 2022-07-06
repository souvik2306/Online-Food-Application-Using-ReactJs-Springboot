import { useState } from "react";
import "./login.css";
import FormInput from "./FormInput";
import Navbar from "../pages/Navbar";
import "bootstrap/dist/css/bootstrap.css";
import Footer from "../pages/Footer";
import service from "../Services/service";
import { useHistory } from "react-router-dom";

const Login = () => {
  let hst = useHistory();
  const [values, setValues] = useState({
    username: "",
    email: "",
    //birthday: "",
    password: "",
    //confirmPassword: "",
  });

  const inputs = [
    /*{
      id: 1,
      name: "username",
      type: "text",
      placeholder: "Username",
      errorMessage:
        "Username should be 3-16 characters and shouldn't include any special character!",
      label: "Username",
      pattern: "^[A-Za-z0-9]{3,16}$",
      required: true,
    },*/
    {
      id: 1,
      name: "email",
      type: "email",
      placeholder: "Email",
      errorMessage: "It should be a valid email address!",
      label: "Email",
      required: true,
    },
    /*{
      id: 3,
      name: "birthday",
      type: "date",
      placeholder: "Birthday",
      label: "Birthday",
    },*/
    {
      id: 2,
      name: "password",
      type: "password",
      placeholder: "Password",
      //errorMessage:
      //  "Password should be 8-20 characters and include at least 1 letter, 1 number and 1 special character!",
      label: "Password",
      //pattern: `^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$`,
      //required: true,
    },
    /*{
      id: 5,
      name: "confirmPassword",
      type: "password",
      placeholder: "Confirm Password",
      errorMessage: "Passwords don't match!",
      label: "Confirm Password",
      pattern: values.password,
      required: true,
    },*/
  ];

  const handleSubmit = (e) => {
    e.preventDefault();
    let cust_email = values.email;
    let cust_pass = values.password;
    service.get(cust_email,cust_pass).then((response) => {
      console.log(response.data);
      service.logout();
      service.createlogin(response.data).then((res)=>{hst.push("/menu");}).catch((err)=>{
        window.alert("Invalid Credentials");
      console.log("Errors",err)})
      })
    .catch((error)=>{console.log("issues",error);
    window.alert("Invalid login credentials");
  })
  };

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };



  return (
    <div>
      <div>
        <Navbar />
      </div>
      <div className="login">
        <form onSubmit={handleSubmit}>
          <h1>Login</h1>
          {inputs.map((input) => (
            <FormInput
              key={input.id}
              {...input}
              value={values[input.name]}
              onChange={onChange}
            />
          ))}
          <center>
            <button className="btn btn-danger" onClick={handleSubmit}>Login</button>
          </center>
          <a href="/signup">
            <center>
              <b>OR, create a new account</b>
            </center>
          </a>
        </form>
      </div>
      <Footer/>
    </div>
  );
};

export default Login;

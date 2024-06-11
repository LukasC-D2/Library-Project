import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../services/AuthContext";
import "../../styles/Navbar.css";
import icon from "../../../book.png"

const Navbar = () => {
  const { token, user, logoutUser } = useAuth(); // Access token and logoutUser method from AuthContext
  const navigate = useNavigate();

  const handleLogout = () => {
    logoutUser(); // Call logoutUser method from AuthContext
    navigate("/");
  };

  return (
    <nav className="navbar">
      <Link to="/" className="icon"><img src={icon} alt="icon" width={40} height={40}/></Link>
      <div className="links">
        {token && (
          <>
            {/* <Link to="/categories" className="homePage">Categories</Link> */}
            <span className="user-name">{user.name}</span>
            <button onClick={handleLogout}>Logout</button>
          </>
        )}
        {!token && (
          <>
            <Link to="/login">Login</Link>
            <Link to="/register">Register</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
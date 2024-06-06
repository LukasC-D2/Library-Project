import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import PrivateRoute from './services/privateRoutes';
import { AuthProvider } from './services/AuthContext';
import Navbar from "./components/pages/Navbar";
import Login from './components/pages/Login';
import Registration from "./components/pages/Registration";

import "./styles/LoginAndRegistration.css";

  function App() {

    return (
      <Router>
        <AuthProvider>
          <div className='App'>
            <Navbar />
            <div className='content'>
              <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Registration />} />
              </Routes>
            </div>
          </div>
        </AuthProvider>
      </Router>
    );
  };

  export default App;
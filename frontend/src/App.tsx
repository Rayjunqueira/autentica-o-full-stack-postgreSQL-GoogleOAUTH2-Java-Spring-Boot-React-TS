import { Routes, Route, useNavigate, useLocation } from 'react-router-dom';

import './App.css';
import Homepage from './pages/Homepage';
import Authpage from './pages/Authpage';
import Signuppage from './pages/Signuppage/Signuppage';
import { RequireAuth } from './context/RequireAuth';
import { RequireNotAuth } from './context/RequireNotAuth';
import React from 'react';
import GoogleAuthRedirect from './pages/GoogleAuthRedirect';

export function InvalidRoute() {
  const navigate = useNavigate();
  React.useEffect(() => {
    navigate('/');
  }, [navigate]);

  return null;
}


function App() {
  return (
    <Routes>
      <Route path='/' element={<RequireAuth><Homepage /></RequireAuth>} />
      <Route path='/auth' element={<RequireNotAuth><Authpage /></RequireNotAuth>} />
      <Route path='/signup' element={<Signuppage />} />
      <Route path='/googleauth/:id/:name/:email/:password' element={<GoogleAuthRedirect />} />
    </Routes>
  );
}

export default App;

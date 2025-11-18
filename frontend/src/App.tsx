import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import AccountPage from "./pages/AccountPage";
import PixPage from "./pages/PixPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />

        <Route path="/login" element={<LoginPage />} />
        <Route path="/account" element={<AccountPage />} />
        <Route path="/pix" element={<PixPage />} />
      </Routes>
    </Router>
  );
}

export default App;
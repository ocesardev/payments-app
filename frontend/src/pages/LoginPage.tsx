import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";
import "./LoginPage.css";

export default function LoginPage() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await api.post("/clients/login", { email, password });
            console.log(response.data);
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("payerId", response.data.id);
            navigate("/account");
        } catch (error) {
            console.log(error);
            alert("Login error!");
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h1 className="login-title">Entrar</h1>

                <input
                    className="login-input"
                    placeholder="Email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />

                <input
                    className="login-input"
                    placeholder="Senha"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />

                <button className="login-button" onClick={handleLogin}>
                    Entrar
                </button>
            </div>
        </div>
    );
};
import { useEffect, useState } from "react";
import api from "../services/api";
import "./AccountPage.css";
import { useNavigate } from "react-router-dom";

export default function AccountPage() {
    const [balance, setBalance] = useState<number | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAccount = async () => {
            const token = localStorage.getItem("token");
            if (!token) return;

            try {
                const response = await api.get("/clients/me", {
                    headers: { Authorization: `Bearer ${token}` },
                });

                setBalance(response.data.balance);
            } catch (err) {
                console.error("Erro ao buscar saldo:", err);
            }
        };

        fetchAccount();
    }, []);

    return (
        <div className="account-container">
            <h1 className="account-title">Conta</h1>

            <div className="balance-card">
                <p className="balance-label">Saldo disponível</p>
                <p className="balance-value">
                    {balance !== null ? `R$ ${balance.toFixed(2)}` : "Carregando..."}
                </p>
            </div>

            <div className="actions-title">Ações</div>

            <div className="actions-grid">
                <button className="action-button" onClick={() => navigate("/pix")}>Pix</button>
                <button className="action-button">Boleto</button>
                <button className="action-button">Transferir</button>
                <button className="action-button">Pagar</button>
            </div>
        </div>
    );
}
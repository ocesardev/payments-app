import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import "./PixPage.css";

export default function PixPage() {
    const [amount, setAmount] = useState("");
    const [payeeId, setPayeeId] = useState("");
    const payerId = Number(localStorage.getItem("payerId"));
    const navigate = useNavigate();

    const handlePix = async () => {
        const token = localStorage.getItem("token");

        try {
            await api.post(
                "/payments",
                {
                    amount: parseFloat(amount),
                    payeeId: Number(payeeId),
                    payerId: payerId,
                    method: "PIX",
                },
                {
                    headers: { Authorization: `Bearer ${token}` }
                }
            );

            alert("PIX enviado com sucesso!");
            navigate("/account");
        } catch (err: any) {
            console.log("ERRO RAW:", err);
            console.log("RESPONSE DATA:", err.response?.data);
            const errorMessage =
                err.response?.data?.message ||
                err.response?.data ||
                "Erro ao enviar PIX";

            alert(errorMessage);
        }
    };

    return (
        <div className="pix-container">
            <h1 className="pix-title">Enviar PIX</h1>

            <input
                className="pix-input"
                type="number"
                placeholder="Valor (R$)"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
            />

            <input
                className="pix-input"
                type="number"
                placeholder="ID do Recebedor"
                value={payeeId}
                onChange={(e) => setPayeeId(e.target.value)}
            />

            <button className="pix-button" onClick={handlePix}>
                Enviar
            </button>

            <button className="pix-back" onClick={() => navigate("/account")}>
                Voltar
            </button>
        </div>
    );
}
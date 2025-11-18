package com.caio.payments.dto;

import com.caio.payments.datasource.model.payment.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentRequest {

    @NotNull(message = "Amount é obrigatório")
    @Positive(message = "Amount deve ser positivo")
    private Double amount;

    @NotNull(message = "Payment method é obrigatório")
    private PaymentMethod method;

    @NotNull(message = "PayerId é obrigatório")
    private Long payerId;

    @NotNull(message = "PayeeId é obrigatório")
    private Long payeeId;

    // Getters e Setters
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public Long getPayerId() { return payerId; }
    public void setPayerId(Long payerId) { this.payerId = payerId; }

    public Long getPayeeId() { return payeeId; }
    public void setPayeeId(Long payeeId) { this.payeeId = payeeId; }
}
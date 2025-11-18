package com.caio.payments.dto;

import java.time.LocalDateTime;

import com.caio.payments.datasource.model.payment.*;

public class PaymentResponse {

    private Long id;
    private Double amount;
    private PaymentStatus status;
    private PaymentMethod method;
    private LocalDateTime createdAt;
    private Long payerId;
    private Long payeeId;

    public static PaymentResponse from(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());
        response.setMethod(payment.getMethod());
        response.setCreatedAt(payment.getCreatedAt());
        response.setPayerId(payment.getPayerId());
        response.setPayeeId(payment.getPayeeId());
        return response;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getPayerId() { return payerId; }
    public void setPayerId(Long payerId) { this.payerId = payerId; }

    public Long getPayeeId() { return payeeId; }
    public void setPayeeId(Long payeeId) { this.payeeId = payeeId; }
}
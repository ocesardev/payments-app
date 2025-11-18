package com.caio.payments.datasource.model.payment;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private LocalDateTime createdAt;

    private Long payerId;
    private Long payeeId;

    public Payment() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

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
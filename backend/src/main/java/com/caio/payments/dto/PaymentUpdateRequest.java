package com.caio.payments.dto;

import com.caio.payments.datasource.model.payment.PaymentMethod;
import com.caio.payments.datasource.model.payment.PaymentStatus;

public class PaymentUpdateRequest {
    private PaymentStatus status;
    private PaymentMethod method;

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }
}
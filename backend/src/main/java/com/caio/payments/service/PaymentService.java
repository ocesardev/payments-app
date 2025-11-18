package com.caio.payments.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.caio.payments.datasource.model.client.Client;
import com.caio.payments.datasource.model.payment.*;
import com.caio.payments.dto.PaymentResponse;
import com.caio.payments.dto.PaymentUpdateRequest;
import com.caio.payments.dto.PaymentRequest;
import com.caio.payments.repository.*;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;


    public PaymentService(ClientRepository clientRepository, PaymentRepository paymentRepository) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponse createPayment(PaymentRequest dto) {

        Client payer = clientRepository.findById(dto.getPayerId())
        		.orElseThrow(() -> new ResponseStatusException(
        		        HttpStatus.BAD_REQUEST,
        		        "Pagador não encontrado"
        		));

        Client payee = clientRepository.findById(dto.getPayeeId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, 
                        "Recebedor não encontrado"
                ));

        if (payer.getBalance() < dto.getAmount()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Saldo insuficiente"
            );
        }

        payer.setBalance(payer.getBalance() - dto.getAmount());
        clientRepository.save(payer);

        payee.setBalance(payee.getBalance() + dto.getAmount());
        clientRepository.save(payee);

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setPayerId(dto.getPayerId());
        payment.setPayeeId(dto.getPayeeId());
        payment.setStatus(PaymentStatus.COMPLETED);

        paymentRepository.save(payment);

        return PaymentResponse.from(payment);
    }
    
    public List<PaymentResponse> getAllPayments() {
    	return paymentRepository.findAll()
    			.stream()
    			.map(PaymentResponse::from)
    			.toList();
    }
    
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        return PaymentResponse.from(payment);
    }
    
    public PaymentResponse updatePayment(Long id, PaymentUpdateRequest dto) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (dto.getMethod() != null) {
            if (payment.getStatus() == PaymentStatus.COMPLETED) {
                throw new RuntimeException("Cannot change payment method after completion");
            }
            payment.setMethod(dto.getMethod());
        }

        if (dto.getStatus() != null) {
            payment.setStatus(dto.getStatus());
        }

        paymentRepository.save(payment);

        return PaymentResponse.from(payment);
    }
    
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        paymentRepository.delete(payment);
    }
}

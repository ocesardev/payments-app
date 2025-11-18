package com.caio.payments.controller;

import com.caio.payments.dto.PaymentRequest;
import com.caio.payments.dto.PaymentResponse;
import com.caio.payments.dto.PaymentUpdateRequest;
import com.caio.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	 @Autowired
	 private PaymentService paymentService;
	 
	 // Create new payment
	 @PostMapping
	 public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
	     PaymentResponse response = paymentService.createPayment(request);
	     return ResponseEntity.ok(response);
	 }
	 
	 // List all payments
	 @GetMapping
	    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
	        List<PaymentResponse> payments = paymentService.getAllPayments();
	        return ResponseEntity.ok(payments);
	    }
	 
	 @PatchMapping("/{id}")
	 public ResponseEntity<PaymentResponse> updatePayment(
	         @PathVariable Long id,
	         @RequestBody PaymentUpdateRequest request) {

	     return ResponseEntity.ok(paymentService.updatePayment(id, request));
	 }
	 
	 // Delete payment (studies purpose only)
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
	        paymentService.deletePayment(id);
	        return ResponseEntity.ok("Payment with id " + id + " deleted successfully");
	    }
}

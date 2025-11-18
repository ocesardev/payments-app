package com.caio.payments.dto;

public record ClientCreateRequest(
	    String name,
	    double balance,
	    String email,
	    String phone,
	    String password
	) {}
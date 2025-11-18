package com.caio.payments.dto;

import com.caio.payments.datasource.model.client.Client;

public record ClientResponse(Long id, String name, double balance,String email, String phone) {
    public static ClientResponse from(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getBalance(),client.getEmail(), client.getPhone());
    }
}
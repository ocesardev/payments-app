package com.caio.payments.service;

import com.caio.payments.dto.ClientResponse;
import com.caio.payments.dto.ClientUpdateRequest;
import com.caio.payments.dto.ClientCreateRequest;
import com.caio.payments.datasource.model.client.Client;
import com.caio.payments.repository.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client login(String email, String password) {
    	Client client = clientRepository.findByEmail(email)
    	        .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    	if (!passwordEncoder.matches(password, client.getPassword())) {
    	    throw new RuntimeException("Invalid credentials");
    	}

    	return client;
    }
    
    public ClientResponse create(ClientCreateRequest request) {
        Client client = new Client();
        client.setName(request.name());
        client.setBalance(request.balance());
        client.setEmail(request.email());
        client.setPhone(request.phone());
        client.setPassword(passwordEncoder.encode(request.password()));
        client.setCreatedAt(LocalDateTime.now());

        Client saved = clientRepository.save(client);
        return ClientResponse.from(saved);
    }
    
    public List<ClientResponse> getAllClients() {
    	return clientRepository.findAll()
    			.stream()
    			.map(ClientResponse::from)
    			.toList();
    }
    
    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found"));
        return ClientResponse.from(client);
    }
    
    public ClientResponse updateClient(Long id, ClientUpdateRequest dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (dto.getName() != null) client.setName(dto.getName());
        if (dto.getEmail() != null) client.setEmail(dto.getEmail());
        if (dto.getPhone() != null) client.setPhone(dto.getPhone());

        clientRepository.save(client);

        return ClientResponse.from(client);
    }
    
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.delete(client);
    }
}
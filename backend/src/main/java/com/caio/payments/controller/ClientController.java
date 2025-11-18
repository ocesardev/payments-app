package com.caio.payments.controller;

import com.caio.payments.datasource.model.client.Client;
import com.caio.payments.dto.ClientCreateRequest;
import com.caio.payments.dto.ClientResponse;
import com.caio.payments.dto.ClientUpdateRequest;
import com.caio.payments.service.ClientService;
import com.caio.payments.security.JwtService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
    private ClientService clientService;
	
	@Autowired
    private JwtService jwtService;
	
	@GetMapping("/me")
	public ResponseEntity<ClientResponse> getMyAccount(@RequestHeader("Authorization") String authHeader) {
	    String token = authHeader.substring(7);
	    Long clientId = jwtService.getClientId(token);

	    ClientResponse client = clientService.getClientById(clientId);
	    return ResponseEntity.ok(client);
	}
	
	@GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        ClientResponse response = clientService.getClientById(id);
        return ResponseEntity.ok(response);
    }
	
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientCreateRequest request) {
        ClientResponse response = clientService.create(request);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(
            @PathVariable Long id,
            @RequestBody ClientUpdateRequest dto) {
        
        ClientResponse updatedClient = clientService.updateClient(id, dto);
        return ResponseEntity.ok(updatedClient);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client with id " + id + " deleted successfully");
    }
}

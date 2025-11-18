package com.caio.payments.controller;

import com.caio.payments.datasource.model.client.Client;
import com.caio.payments.dto.LoginRequest;
import com.caio.payments.dto.LoginResponse;
import com.caio.payments.service.ClientService;
import com.caio.payments.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients") // rota base
public class AuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        Client client = clientService.login(req.email(), req.password());
        String token = jwtService.generateToken(client.getId(), client.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, client.getId()));
    }
}

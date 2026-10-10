package com.bootlabs.authapi.controller;

import com.bootlabs.authapi.dto.LoginRequest;
import com.bootlabs.authapi.dto.LoginResponse;
import com.bootlabs.authapi.dto.WhoAmIResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

// Stub identity endpoint for the Ingress routing demo only — it issues an
// unsigned, unverifiable token and must never be treated as a real auth service.
@RestController
public class AuthController {

    private final String podName = System.getenv().getOrDefault("POD_NAME", "local");

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        String token = UUID.randomUUID().toString();
        return new LoginResponse(request.username(), token, podName);
    }

    @GetMapping("/whoami")
    public WhoAmIResponse whoAmI() {
        return new WhoAmIResponse("anonymous", podName);
    }
}

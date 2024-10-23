package com.minguard.controller;

import com.minguard.dto.user.LoginRequest;
import com.minguard.dto.user.LoginResponse;
import com.minguard.service.spec.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Auth Controller")
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Logs-in with e-mail and password.")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {

        LoginResponse loginResponse = authService.authenticate(request);

        return ResponseEntity.ok(loginResponse);
    }

}

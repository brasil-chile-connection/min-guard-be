package com.minguard.service.impl;

import com.minguard.dto.user.LoginRequest;
import com.minguard.dto.user.LoginResponse;
import com.minguard.repository.UserRepository;
import com.minguard.service.spec.AuthService;
import com.minguard.service.spec.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final var authenticatedUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + request.getEmail() + " not found"));

        final var jwtToken = jwtService.generateToken(authenticatedUser);

        return new LoginResponse(authenticatedUser.getId(),
                jwtToken,
                jwtService.getExpirationTime()
        );
    }
}

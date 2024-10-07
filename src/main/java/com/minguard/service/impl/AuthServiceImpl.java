package com.minguard.service.impl;

import com.minguard.dto.user.LoginRequest;
import com.minguard.dto.user.LoginResponse;
import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.entity.User;
import com.minguard.mapper.UserMapper;
import com.minguard.repository.UserRepository;
import com.minguard.service.spec.AuthService;
import com.minguard.service.spec.JwtService;
import com.minguard.service.spec.UserService;
import com.minguard.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterUserResponse signup(RegisterUserRequest request) {
        assertPasswordsMatch(request);
        assertTermsAcceptance(request);

        User user = UserMapper.INSTANCE.fromRegisterRequest(request, passwordEncoder.encode(request.password()));

        userService.assignRole(user, Roles.ROLE_CUSTOMER);

        return UserMapper.INSTANCE.toRegisterResponse(userRepository.save(user));
    }

    private void assertTermsAcceptance(RegisterUserRequest request) {
        if (!request.acceptTc()) {
            throw new IllegalArgumentException("Terms and conditions must be accepted to proceed.");
        }
    }

    private void assertPasswordsMatch(RegisterUserRequest request) {
        if (!request.password().equals(request.passwordConfirm())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final var authenticatedUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        final var jwtToken = jwtService.generateToken(authenticatedUser);

        return new LoginResponse(authenticatedUser.getId(),
                jwtToken,
                jwtService.getExpirationTime()
        );
    }
}

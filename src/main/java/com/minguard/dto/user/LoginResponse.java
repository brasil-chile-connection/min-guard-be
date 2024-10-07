package com.minguard.dto.user;

public record LoginResponse(Long id, String token, long expiresIn) {
}

package com.minguard.service.spec;

import com.minguard.dto.user.LoginRequest;
import com.minguard.dto.user.LoginResponse;
import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;

public interface AuthService {

    LoginResponse authenticate(LoginRequest request);

}

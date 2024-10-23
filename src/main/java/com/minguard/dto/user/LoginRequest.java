package com.minguard.dto.user;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email" + ValidationMessage.MUST_BE_SPECIFIED)
    @Email(message = "email has incorrect format")
    private String email;

    @NotBlank(message = "password" + ValidationMessage.MUST_BE_SPECIFIED)
    private String password;
};

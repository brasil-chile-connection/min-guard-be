package com.minguard.dto.user;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterUserRequest(
        @NotBlank(message = "email" + ValidationMessage.MUST_BE_SPECIFIED)
        @Email(message = "email has incorrect format")
        String email,

        @NotBlank(message = "password" + ValidationMessage.MUST_BE_SPECIFIED)
        @Pattern(message = "password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
        String password,

        @NotBlank(message = "password_confirm" + ValidationMessage.MUST_BE_SPECIFIED)
        @Pattern(message = "confirm_password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
        String passwordConfirm,

        @NotBlank(message = "first_name" + ValidationMessage.MUST_BE_SPECIFIED)
        String firstName,

        @NotBlank(message = "last_name" + ValidationMessage.MUST_BE_SPECIFIED)
        String lastName,

        @NotBlank(message = "mobile_prefix" + ValidationMessage.MUST_BE_SPECIFIED)
        @Pattern(message = "mobile_prefix must contain plus sign and digits", regexp = "^[+]\\d+$")
        String mobilePrefix,

        @NotBlank(message = "mobile_number" + ValidationMessage.MUST_BE_SPECIFIED)
        @Pattern(message = "mobile_number must contain numbers", regexp = "\\d+$")
        String mobileNumber,

        @NotNull(message = "accept_tp" + ValidationMessage.MUST_BE_SPECIFIED)
        @AssertTrue(message = "Terms and conditions must be accepted to proceed")
        Boolean acceptTc,

        @NotNull(message = "gender_id" + ValidationMessage.MUST_BE_SPECIFIED)
        Integer genderId,

        @NotNull(message = "role_id" + ValidationMessage.MUST_BE_SPECIFIED)
        Integer roleId,

        String biography
) {
};

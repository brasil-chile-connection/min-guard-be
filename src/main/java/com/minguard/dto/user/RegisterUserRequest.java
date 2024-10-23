package com.minguard.dto.user;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "email" + ValidationMessage.MUST_BE_SPECIFIED)
    @Email(message = "email has incorrect format")
    private String email;

    @NotBlank(message = "password" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
    private String password;

    @NotBlank(message = "passwordConfirm" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "passwordConfirm must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter and 1 digit", regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
    private String passwordConfirm;

    @NotBlank(message = "firstName" + ValidationMessage.MUST_BE_SPECIFIED)
    private String firstName;

    @NotBlank(message = "lastName" + ValidationMessage.MUST_BE_SPECIFIED)
    private String lastName;

    @NotBlank(message = "mobilePrefix" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "mobilePrefix must contain plus sign and digits", regexp = "^[+]\\d+$")
    private String mobilePrefix;

    @NotBlank(message = "mobileNumber" + ValidationMessage.MUST_BE_SPECIFIED)
    @Pattern(message = "mobileNumber must contain numbers", regexp = "\\d+$")
    private String mobileNumber;

    @NotNull(message = "acceptTc" + ValidationMessage.MUST_BE_SPECIFIED)
    @AssertTrue(message = "Terms and conditions must be accepted to proceed")
    private Boolean acceptTc;

    @NotNull(message = "genderId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long genderId;
}

package com.br.expenses.infrastructure.ports.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequestDto(
        @NotBlank(message = "NAME_IS_REQUIRED")
        @Pattern(regexp = "^[A-Z][a-z]+$", message = "ONLY_FIRST_NAME_WITH_FIRST_LETTER_UPPERCASE")
        String name,

        @NotBlank(message = "EMAIL_IS_REQUIRED")
        @Email(message = "EMAIL_MUST_BE_A_VALID_EMAIL")
        String email,
        
        @NotBlank(message = "PASSWORD_IS_REQUIRED")
        @Pattern(regexp = ".*[0-9].*", message = "PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_NUMBER")
        @Pattern(regexp = ".*[A-Z].*", message = "PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_UPPERCASE_LETTER")
        @Pattern(regexp = ".*[a-z].*", message = "PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_LOWERCASE_LETTER")
        @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", message = "PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_SPECIAL_CHARACTER")
        @Pattern(regexp = ".{8,}", message = "PASSWORD_MUST_BE_AT_LEAST_8_CHARACTERS_LONG")
        String password,

        @NotBlank(message = "PASSWORD_CONFIRMATION_REQUIRED")
        String passwordConfirmation
) {
}

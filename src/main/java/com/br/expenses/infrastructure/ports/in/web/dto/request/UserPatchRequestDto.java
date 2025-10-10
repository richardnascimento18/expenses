package com.br.expenses.infrastructure.ports.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserPatchRequestDto(
        @Pattern(regexp = "^[A-Z][a-z]+$", message = "ONLY_FIRST_NAME_WITH_FIRST_LETTER_UPPERCASE")
        String name
) {
}

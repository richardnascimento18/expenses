package com.br.expenses.infrastructure.ports.in.web.dto.response;

import java.util.UUID;

public record UserResponseDto (UUID id, String name, String email) {
}

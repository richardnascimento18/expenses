package com.br.expenses.infrastructure.ports.in.web.dto.response;

import com.br.expenses.domain.model.Expense;

import java.util.List;
import java.util.UUID;

public record UserWithExpenseResponseDto(UUID id, String name, String email, List<Expense> expense) {
}

package com.br.expenses.infrastructure.model.User;

import com.br.expenses.domain.model.Expense;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "users")
public class UserImplementation {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private List<Expense> expenses;

    public UserImplementation(UUID id, String name, String email, String passwordHash, List<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.expenses = expenses;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}

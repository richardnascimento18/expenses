package com.br.expenses.domain.model;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Expense> expenses;

    public User() {}

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.expenses = List.of();
    }

    public User(UUID id, String name, String email, String password, List<Expense> expenses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.expenses = expenses;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public List<Expense> getExpenses() { return expenses; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }
}

package com.br.expenses.domain.exceptions;

public class PasswordsDoNotCoincideException extends RuntimeException{
    public PasswordsDoNotCoincideException() {
        super("THE_GIVEN_PASSWORDS_DO_NOT_COINCIDE");
    }
}

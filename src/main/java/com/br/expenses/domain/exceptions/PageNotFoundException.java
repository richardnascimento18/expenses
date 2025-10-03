package com.br.expenses.domain.exceptions;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String message) {
        super("PAGE_NOT_FOUND_AT_" + message);
    }
}

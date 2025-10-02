package com.br.expenses.infrastructure.ports.in.web.dto.response;

public class ErrorDto {
    private String code;
    private String error;

    public ErrorDto(String code, String error) {
        this.code = code;
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}


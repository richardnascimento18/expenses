package com.br.expenses.infrastructure.ports.in.web;

import com.br.expenses.domain.exceptions.PasswordsDoNotCoincideException;
import com.br.expenses.infrastructure.ports.in.web.dto.response.ApiResponseDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.ErrorDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @Value("${app.version}")
    private String appVersion;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<ErrorDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        ErrorDto errorDto = new ErrorDto("#400", errorMessage);

        ApiResponseDto<ErrorDto> response = new ApiResponseDto<>(
                HttpStatus.BAD_REQUEST.value(),
                appVersion,
                errorDto,
                Map.of()
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(PasswordsDoNotCoincideException.class)
    public ResponseEntity<ApiResponseDto<Object>> handlePasswordsDoNotCoincide(PasswordsDoNotCoincideException ex) {
        ApiResponseDto<Object> response = new ApiResponseDto<>(
                HttpStatus.BAD_REQUEST.value(),
                appVersion,
                Map.of("error", "Passwords do not coincide", "details", ex.getMessage()),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Object>> handleGeneric(Exception ex) {
        ApiResponseDto<Object> response = new ApiResponseDto<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                appVersion,
                Map.of("error", "Unexpected error", "details", ex.getMessage()),
                Map.of()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

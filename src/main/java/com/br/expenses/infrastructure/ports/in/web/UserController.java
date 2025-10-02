package com.br.expenses.infrastructure.ports.in.web;

import com.br.expenses.application.service.UserService;
import com.br.expenses.domain.model.User;
import com.br.expenses.infrastructure.ports.in.web.dto.request.UserRequestDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.ApiResponseDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Value("${app.version}")
    private String API_VERSION;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponseDto<UserResponseDto> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        User created = userService.register(userRequestDto.name(), userRequestDto.email(), userRequestDto.password(), userRequestDto.passwordConfirmation());
        UserResponseDto dto = new UserResponseDto(created.getId(), created.getName(), created.getEmail());

        Map<String, ApiResponseDto.Link> links = new LinkedHashMap<>();
        links.put("previous", new ApiResponseDto.Link("GET", "http://localhost:8080/users", "all-users"));
        links.put("current", new ApiResponseDto.Link("POST", "http://localhost:8080/users", "create-user"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/" + created.getId(), "get-user"));

        return new ApiResponseDto<>(201, API_VERSION, dto, links);
    }
}

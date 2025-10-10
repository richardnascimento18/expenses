package com.br.expenses.infrastructure.ports.in.web;

import com.br.expenses.application.service.UserService;
import com.br.expenses.domain.model.User;
import com.br.expenses.infrastructure.ports.in.web.dto.request.UserPatchRequestDto;
import com.br.expenses.infrastructure.ports.in.web.dto.request.UserRequestDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.ApiResponseDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.UserDeletedResponseDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.UserResponseDto;
import com.br.expenses.infrastructure.ports.in.web.dto.response.UserWithExpenseResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        links.put("previous", new ApiResponseDto.Link("GET", "http://localhost:8080/users/{page}", "all-users"));
        links.put("current", new ApiResponseDto.Link("POST", "http://localhost:8080/users/", "create-user"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/" + created.getId(), "get-user"));

        return new ApiResponseDto<>(201, API_VERSION, dto, links);
    }

    @GetMapping("/page/{page}")
    public ApiResponseDto<List<UserResponseDto>> findAll(@PathVariable int page) {
        List<User> users = userService.findAll(page);
        List<UserResponseDto> dtos = users.stream().map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail())).toList();

        int previous = page > 1 ? page - 1 : 0;
        int next = page + 1;

        Map<String, ApiResponseDto.Link> links = new LinkedHashMap<>();
        if(page != 0) {
            links.put("previous", new ApiResponseDto.Link("GET", "http://localhost:8080/users/" + previous, "all-users"));
        }
        links.put("current", new ApiResponseDto.Link("POST", "http://localhost:8080/users/" + page, "all-users"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/" + next, "all-users"));

        return new ApiResponseDto<>(200, API_VERSION, dtos, links);
    }

    @GetMapping("/{id}")
    public ApiResponseDto<UserWithExpenseResponseDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        UserWithExpenseResponseDto dto = new UserWithExpenseResponseDto(user.getId(), user.getName(), user.getEmail(), user.getExpenses());

        Map<String, ApiResponseDto.Link> links = new LinkedHashMap<>();
        links.put("previous", new ApiResponseDto.Link("POST", "http://localhost:8080/users/", "create-user"));
        links.put("current", new ApiResponseDto.Link("GET", "http://localhost:8080/users/" + user.getId(), "get-user"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/{page}", "all-users"));

        return new ApiResponseDto<>(200, API_VERSION, dto, links);
    }

    @DeleteMapping("/{id}")
    public ApiResponseDto<UserDeletedResponseDto> delete(@PathVariable String id) {
        userService.deleteById(id);
        UserDeletedResponseDto dto = new UserDeletedResponseDto("USER_WITH_ID_" + id + "_WAS_DELETED");

        Map<String, ApiResponseDto.Link> links = new LinkedHashMap<>();
        links.put("previous", new ApiResponseDto.Link("POST", "http://localhost:8080/users/", "create-user"));
        links.put("current", new ApiResponseDto.Link("DELETE", "http://localhost:8080/users/" + id, "delete-user"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/{page}", "all-users"));

        return new ApiResponseDto<>(200, API_VERSION, dto, links);
    }

    @PatchMapping("/{id}")
    public ApiResponseDto<UserResponseDto> update(@PathVariable String id, @Valid @RequestBody UserPatchRequestDto userPatchRequestDto) {
        User updated = userService.update(id, userPatchRequestDto);
        UserResponseDto dto = new UserResponseDto(updated.getId(), updated.getName(), updated.getEmail());

        Map<String, ApiResponseDto.Link> links = new LinkedHashMap<>();
        links.put("previous", new ApiResponseDto.Link("POST", "http://localhost:8080/users/", "create-user"));
        links.put("current", new ApiResponseDto.Link("PATCH", "http://localhost:8080/users/" + id, "update-user"));
        links.put("next", new ApiResponseDto.Link("GET", "http://localhost:8080/users/{page}", "all-users"));

        return new ApiResponseDto<>(200, API_VERSION, dto, links);
    }
}

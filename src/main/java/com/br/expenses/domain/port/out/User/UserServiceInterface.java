package com.br.expenses.domain.port.out.User;

import com.br.expenses.domain.model.User;
import com.br.expenses.infrastructure.ports.in.web.dto.request.UserPatchRequestDto;

import java.util.List;

public interface UserServiceInterface {
    User register(String name, String email, String password, String passwordConfirmation);
    User findById(String id);
    List<User> findAll(int page);
    void deleteById(String id);
    User update(String id, UserPatchRequestDto user);
}

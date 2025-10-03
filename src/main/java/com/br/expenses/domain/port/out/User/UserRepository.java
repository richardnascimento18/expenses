package com.br.expenses.domain.port.out.User;

import com.br.expenses.domain.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findById(String id);
    List<User> findAll(int page);
}

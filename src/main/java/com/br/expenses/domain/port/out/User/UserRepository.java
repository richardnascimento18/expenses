package com.br.expenses.domain.port.out.User;

import com.br.expenses.domain.model.User;

public interface UserRepository {
    User save(User user);
}

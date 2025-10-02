package com.br.expenses.domain.port.out.User;

import com.br.expenses.domain.model.User;

public interface UserServiceInterface {
    User register(String name, String email, String password, String passwordConfirmation);
}

package com.br.expenses.application.service;

import com.br.expenses.domain.exceptions.PageNotFoundException;
import com.br.expenses.domain.exceptions.PasswordsDoNotCoincideException;
import com.br.expenses.domain.exceptions.UserNotFoundException;
import com.br.expenses.domain.model.User;
import com.br.expenses.domain.port.out.User.UserRepository;
import com.br.expenses.domain.port.out.User.UserServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String name, String email, String password, String passwordConfirmation) {
        if(!password.equals(passwordConfirmation)) throw new PasswordsDoNotCoincideException();
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(name, email, hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        User user = userRepository.findById(id);
        if(user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public List<User> findAll(int page) {
        List<User> users = userRepository.findAll(page);
        if(users.isEmpty()) throw new PageNotFoundException("USERS");
        return users;
    }
}

package com.br.expenses.infrastructure.config;

import com.br.expenses.application.service.UserService;
import com.br.expenses.domain.port.out.User.UserRepository;
import com.br.expenses.domain.port.out.User.UserServiceInterface;
import com.br.expenses.infrastructure.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        return new UserService(userRepository, passwordEncoder, userMapper);
    }
}

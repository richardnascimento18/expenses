package com.br.expenses.infrastructure.model.User;

import com.br.expenses.domain.model.User;
import com.br.expenses.domain.port.out.User.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementationAdapter implements UserRepository {
    private final UserRepositoryImplementation userRepositoryImplementation;

    public UserRepositoryImplementationAdapter(UserRepositoryImplementation userRepositoryImplementation) {
        this.userRepositoryImplementation = userRepositoryImplementation;
    }

    @Override
    public User save(User user) {
        UserImplementation userImplementation = new UserImplementation(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getExpenses());
        userRepositoryImplementation.save(userImplementation);
        return user;
    }
}

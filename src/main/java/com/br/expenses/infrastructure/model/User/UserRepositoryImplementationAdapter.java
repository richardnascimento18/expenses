package com.br.expenses.infrastructure.model.User;

import com.br.expenses.domain.model.User;
import com.br.expenses.domain.port.out.User.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public User findById(String id) {
        Optional<UserImplementation> userImplementation = userRepositoryImplementation.findById(UUID.fromString(id));
        return userImplementation.map(implementation -> new User(implementation.getId(), implementation.getName(), implementation.getEmail(), implementation.getPasswordHash(), implementation.getExpenses())).orElse(null);
    }

    @Override
    public List<User> findAll(int page) {
        Pageable pageable = Pageable.ofSize(15).withPage(page);
        List<UserImplementation> userImplementationList = userRepositoryImplementation.findAll(pageable).toList();

        return userImplementationList.stream().map(implementation -> new User(implementation.getId(), implementation.getName(), implementation.getEmail(), implementation.getPasswordHash(), implementation.getExpenses())).toList();
    }
}

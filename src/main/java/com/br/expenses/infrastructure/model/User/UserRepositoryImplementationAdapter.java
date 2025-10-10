package com.br.expenses.infrastructure.model.User;

import com.br.expenses.domain.model.User;
import com.br.expenses.domain.port.out.User.UserRepository;
import com.br.expenses.infrastructure.mapper.UserImplementationMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImplementationAdapter implements UserRepository {
    private final UserRepositoryImplementation userRepositoryImplementation;
    private final UserImplementationMapper userImplementationMapper;

    public UserRepositoryImplementationAdapter(UserRepositoryImplementation userRepositoryImplementation, UserImplementationMapper userImplementationMapper) {
        this.userRepositoryImplementation = userRepositoryImplementation;
        this.userImplementationMapper = userImplementationMapper;
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

    @Override
    public void deleteById(String id) {
        userRepositoryImplementation.deleteById(UUID.fromString(id));
    }

    @Override
    public boolean existsById(String id) {
        return userRepositoryImplementation.existsById(UUID.fromString(id));
    }

    @Override
    public User update(String id, User user) {
        UUID uuid = UUID.fromString(id);
        UserImplementation existingUser = userRepositoryImplementation.findById(uuid).orElseThrow();
        userImplementationMapper.updateImplementationFromDomain(user, existingUser);
        UserImplementation saved = userRepositoryImplementation.save(existingUser);
        return userImplementationMapper.toDomain(saved);
    }
}

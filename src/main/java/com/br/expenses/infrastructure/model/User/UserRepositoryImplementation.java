package com.br.expenses.infrastructure.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepositoryImplementation extends MongoRepository<UserImplementation, UUID> {
}

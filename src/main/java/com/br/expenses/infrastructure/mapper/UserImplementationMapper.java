package com.br.expenses.infrastructure.mapper;

import com.br.expenses.domain.model.User;
import com.br.expenses.infrastructure.model.User.UserImplementation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserImplementationMapper {

    @Mapping(source = "password", target = "passwordHash")
    UserImplementation toImplementation(User user);

    @Mapping(source = "passwordHash", target = "password")
    User toDomain(UserImplementation implementation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "password", target = "passwordHash")
    void updateImplementationFromDomain(User user, @MappingTarget UserImplementation implementation);
}


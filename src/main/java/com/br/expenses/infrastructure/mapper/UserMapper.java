package com.br.expenses.infrastructure.mapper;

import com.br.expenses.domain.model.User;
import com.br.expenses.infrastructure.ports.in.web.dto.request.UserPatchRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // 🔹 Update entity with non-null values from patch DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserPatchRequestDto dto, @MappingTarget User user);
}


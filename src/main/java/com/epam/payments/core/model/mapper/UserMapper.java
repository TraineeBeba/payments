package com.epam.payments.core.model.mapper;

import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    UserEntity toEntity(UserDTO dto);

    UserDTO toDTO(UserEntity entity);
}
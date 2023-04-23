package com.epam.payments.core.model.mapper;

import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TransferEntity toEntity(TransferDTO dto);

    TransferDTO toDTO(TransferEntity entity);
}

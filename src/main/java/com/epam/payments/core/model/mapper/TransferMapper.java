package com.epam.payments.core.model.mapper;

import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface TransferMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    TransferEntity toEntity(TransferDTO dto);

    TransferDTO toDTO(TransferEntity entity);
}

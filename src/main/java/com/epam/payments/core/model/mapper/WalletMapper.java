package com.epam.payments.core.model.mapper;

import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface WalletMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    WalletEntity toEntity(WalletDTO dto);

    WalletDTO toDTO(WalletEntity entity);
}
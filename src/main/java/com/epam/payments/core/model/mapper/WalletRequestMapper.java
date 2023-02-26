package com.epam.payments.core.model.mapper;

import com.epam.payments.core.model.dto.WalletRequestDTO;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface WalletRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    WalletRequestEntity toEntity(WalletRequestDTO dto);

    WalletRequestDTO toDTO(WalletRequestEntity entity);
}

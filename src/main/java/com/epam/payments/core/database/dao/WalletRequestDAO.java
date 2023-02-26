package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;

import java.util.List;

public interface WalletRequestDAO extends DAO<WalletRequestEntity> {

    boolean existsByWalletIdAndStatusID(Long walletId, Long status_id);

    List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int recordsPerPage, RequestStatus status);


}

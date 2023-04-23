package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;

import java.util.List;

public interface WalletRequestDAO extends DAO<WalletRequestEntity> {

    boolean existsByWalletIdAndStatus(Long walletId, RequestStatus requestStatus);

    List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int recordsPerPage, RequestStatus status);

    int getNoOfRecords();

    WalletRequestEntity findByWalletIDandRequestStatus(Long walletId, RequestStatus requestStatus);
}

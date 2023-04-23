package com.epam.payments.core.service.wallet_request;

import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.model.enums.type.WalletRequestType;
import com.epam.payments.core.service.Service;

import java.util.List;

public interface WalletRequestService extends Service {
    boolean existsByWalletIdAndStatus(Long walletId, RequestStatus requestStatus);

    void create(WalletEntity wallet, RequestStatus inProcess, WalletRequestType walletRequestType);

    int getNoOfRecords();

    List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int defaultRecordsPerPage, RequestStatus inProcess);

    void confirmByWalletIdAndStatus(Long id, RequestStatus inProcess);
}

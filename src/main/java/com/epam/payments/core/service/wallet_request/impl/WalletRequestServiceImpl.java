package com.epam.payments.core.service.wallet_request.impl;

import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.model.enums.type.WalletRequestType;
import com.epam.payments.core.service.wallet_request.WalletRequestService;

import java.util.List;


public class WalletRequestServiceImpl implements WalletRequestService {
    private final WalletRequestDAO walletRequestDAO;

    public WalletRequestServiceImpl(WalletRequestDAO walletRequestDAO) {
        this.walletRequestDAO = walletRequestDAO;
    }

    public List<WalletRequestEntity> findAll() {
        return walletRequestDAO.findAll();
    }

    public WalletRequestEntity findById(Long id) {
        return walletRequestDAO.findById(id);
    }

    public void save(WalletRequestEntity entity) {
        walletRequestDAO.save(entity);
    }

    public void update(WalletRequestEntity entity) {
        walletRequestDAO.update(entity);
    }

    @Override
    public boolean existsByWalletIdAndStatus(Long walletId, RequestStatus requestStatus) {
        return walletRequestDAO.existsByWalletIdAndStatus(walletId, requestStatus);
    }

    @Override
    public void confirmByWalletIdAndStatus(Long walletId, RequestStatus requestStatus) {
        WalletRequestEntity entity = walletRequestDAO.findByWalletIDandRequestStatus(walletId, requestStatus);
        entity.setStatus(RequestStatus.AFFIRMATIVE);
        this.update(entity);
    }

    @Override
    public void create(WalletEntity wallet, RequestStatus requestStatus, WalletRequestType walletRequestType) {
        WalletRequestEntity walletRequestEntity = new WalletRequestEntity();
        walletRequestEntity.setWallet_id(wallet.getId());
        walletRequestEntity.setStatus(requestStatus);
        walletRequestEntity.setType(walletRequestType);

        this.save(walletRequestEntity);
    }

    @Override
    public List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int recordsPerPage, RequestStatus status) {
        return walletRequestDAO.getSortedList(
                walletRequestSort,
                offset,
                recordsPerPage,
                status
        );
    }

    @Override
    public int getNoOfRecords() {
        return walletRequestDAO.getNoOfRecords();
    }
}

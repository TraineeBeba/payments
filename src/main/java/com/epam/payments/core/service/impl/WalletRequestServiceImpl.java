package com.epam.payments.core.service.impl;

import com.epam.payments.core.database.dao.WalletRequestDAO;
import com.epam.payments.core.model.entity.WalletRequestEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.service.WalletRequestService;

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


    public boolean existsByWalletIdAndStatusID(Long walletId, Long status_id) {
        return walletRequestDAO.existsByWalletIdAndStatusID(walletId, status_id);
    }

    public List<WalletRequestEntity> getSortedList(String walletRequestSort, int offset, int recordsPerPage, RequestStatus status) {
        return walletRequestDAO.getSortedList(
                walletRequestSort,
                offset,
                recordsPerPage,
                status
        );
    }
}

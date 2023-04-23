package com.epam.payments.core.database.dao;

import com.epam.payments.core.model.entity.TransferEntity;

import java.util.List;

public interface TransferDAO extends DAO<TransferEntity> {
    List<TransferEntity> getSortedListByBill(int bill_number, String sortBy, int page, int noOfRecords);

    int getNoOfRecords();
}

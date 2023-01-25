package com.epam.payments.db.dao;

import com.epam.payments.db.dto.TransferDTO;

import java.util.List;

public interface ITransferDAO {

    void createTransfer(TransferDTO transferDTO);


    List<TransferDTO> findTransfersBySenderBill(int bill_number, String sortBy, int offset, int noOfRecords);

    int getNoOfRecords();
}

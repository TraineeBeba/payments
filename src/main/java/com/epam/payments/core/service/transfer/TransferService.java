package com.epam.payments.core.service.transfer;

import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.service.Service;
import com.epam.payments.core.service.transfer.exception.PrepareTransferException;
import com.epam.payments.core.service.transfer.exception.SendTransferException;
import com.epam.payments.core.service.wallet.WalletService;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService extends Service {
    List<TransferDTO> getSortedTransfersByBill(int bill_number, String transferSort, int offset, int defaultRecordsPerPage);

    TransferDTO prepareTransfer(WalletService walletService, int senderBillNumber, int recipientBillNumber, BigDecimal sum) throws PrepareTransferException;

    int getNoOfRecords();

    void sendTransfer(WalletService walletService, TransferDTO transferDTO) throws SendTransferException;
}

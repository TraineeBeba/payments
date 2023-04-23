package com.epam.payments.core.service.transfer.impl;

import com.epam.payments.command.navigation.user.transfer.GoPrepareTransferCommand;
import com.epam.payments.core.database.dao.TransferDAO;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.enums.state.WalletState;
import com.epam.payments.core.model.enums.status.TransferStatus;
import com.epam.payments.core.model.mapper.TransferMapper;
import com.epam.payments.core.model.mapper.UserMapper;
import com.epam.payments.core.service.transfer.exception.PrepareTransferException;
import com.epam.payments.core.service.transfer.exception.SendTransferException;
import com.epam.payments.core.service.transfer.exception.ValidTransferException;
import com.epam.payments.core.service.wallet.WalletNotFoundException;
import com.epam.payments.core.service.transfer.TransferService;
import com.epam.payments.core.service.wallet.WalletService;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TransferServiceImpl implements TransferService {
    public static final Logger LOG = Logger.getLogger(TransferServiceImpl.class);
    private final TransferDAO transferDAO;

    public TransferServiceImpl(TransferDAO transferDAO) {
        this.transferDAO = transferDAO;
    }

    @Override
    public TransferDTO prepareTransfer(WalletService walletService, int senderBillNumber, int recipientBillNumber, BigDecimal sum) throws PrepareTransferException {
        try {
            WalletDTO senderWallet = walletService.getWalletByBill(senderBillNumber);
            WalletDTO recipientWallet = walletService.getWalletByBill(recipientBillNumber);

            isValidTransfer(senderWallet, recipientWallet, sum);
        } catch (WalletNotFoundException | ValidTransferException e) {
            throw new PrepareTransferException(e.getMessage());
        }

        //TODO synchronized

        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setStatus(TransferStatus.PREPARED);
        transferDTO.setSender_bill_number(senderBillNumber);
        transferDTO.setRecipient_bill_number(recipientBillNumber);
        transferDTO.setSum(sum);

        return transferDTO;
    }

    @Override
    public void sendTransfer(WalletService walletService, TransferDTO transferDTO) throws SendTransferException {
        TransferEntity transferEntity;
        try {
            transferEntity = walletService.doTransfer(transferDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        transferDAO.save(transferEntity);
    }

    @Override
    public List<TransferDTO> getSortedTransfersByBill(int bill_number, String sortBy, int offset, int recordPerPage) {
        List<TransferEntity> transferEntities = transferDAO.getSortedListByBill(
                bill_number,
                sortBy,
                offset,
                recordPerPage
        );

        List<TransferDTO> transferDTOS = new ArrayList<>();
        for (TransferEntity transferEntity : transferEntities) {
            transferDTOS.add(TransferMapper.INSTANCE.toDTO(transferEntity));
        }

        return transferDTOS;
    }

    @Override
    public int getNoOfRecords() {
        return transferDAO.getNoOfRecords();
    }

    private void isValidTransfer(WalletDTO senderWallet, WalletDTO recipientWallet, BigDecimal sum) throws ValidTransferException {
        if (recipientWallet.getBill_number() == senderWallet.getBill_number()){
            throw new ValidTransferException("alertError.bad_selftransfer");
        }

        if (senderWallet.getBalance().compareTo(sum) < 0){
            throw new ValidTransferException("alertError.few_balance");
        }

        if (recipientWallet.getState().equals(WalletState.BLOCKED)){
            throw new ValidTransferException("alertError.wallet_blocked");
        }
    }
}

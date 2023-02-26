package com.epam.payments.core.service.impl;

import com.epam.payments.core.database.dao.TransferDAO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.service.TransferService;

import java.util.List;


public class TransferServiceImpl implements TransferService {
    private static TransferServiceImpl INSTANCE;
    private final TransferDAO transferDAO;

    public TransferServiceImpl(TransferDAO transferDAO) {
        this.transferDAO = transferDAO;
    }

    @Override
    public String isValidTransfer(TransferEntity transferEntity) {
        if (transferEntity.getRecipient_bill_number() == transferEntity.getSender_bill_number()){
            return "alertError.bad_selftransfer";
        }

//        WalletDTO senderWallet = walletDAO.getByBill(transferDTO.getSender_bill_number());
//        WalletDTO recipientWallet = walletDAO.getByBill(transferDTO.getRecipient_bill_number());
//        if (recipientWallet == null || senderWallet == null) {
//            return "alertError.no_wallet";
//        }
//
//        if (Double.compare(senderWallet.getBalance(), transferDTO.getSum()) < 0){
//            return "alertError.few_balance";
//        }
//
//        if (recipientWallet.getState().equals(WalletState.BLOCKED.getName())){
//            return "alertError.wallet_blocked";
//        }

//        if (Double.compare(senderWallet.getBalance() + transferDTO.getSum(), MAX_BALANCE) > 0){
//            return "alertError.max-top-up";
//        } TODO

        return null;
    }

    public List<TransferEntity> findAll() {
        return transferDAO.findAll();
    }

    public TransferEntity findById(Long id) {
        return transferDAO.findById(id);
    }

    public void save(TransferEntity entity) {
        transferDAO.save(entity);
    }

    public void update(TransferEntity entity) {
        transferDAO.update(entity);
    }

    public List<TransferEntity> getSortedListByBill(int bill_number, String sortBy, int offset, int recordPerPage) {
        return transferDAO.getSortedListByBill(
                bill_number,
                sortBy,
                offset,
                recordPerPage
        );
    }
}

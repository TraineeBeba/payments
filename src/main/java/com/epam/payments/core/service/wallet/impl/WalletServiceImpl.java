package com.epam.payments.core.service.wallet.impl;

import com.epam.payments.command.constant.SortConstants;
import com.epam.payments.core.database.dao.UserDAO;
import com.epam.payments.core.database.dao.WalletDAO;
import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.state.WalletState;
import com.epam.payments.core.model.mapper.WalletMapper;
import com.epam.payments.core.service.wallet.AbstractWallet;
import com.epam.payments.core.service.wallet.WalletService;
import com.epam.payments.core.service.wallet.exception.MaxBalanceException;
import com.epam.payments.core.service.wallet.exception.WalletCreationException;
import com.epam.payments.core.service.wallet.exception.WalletNotFoundException;
import com.epam.payments.core.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WalletServiceImpl extends AbstractWallet implements WalletService {
    public WalletServiceImpl(WalletDAO walletDAO, UserDAO userDAO) {
        super(walletDAO, userDAO);
    }

    @Override
    public void topUpWallet(int bill_number, BigDecimal sum) throws MaxBalanceException {
        WalletEntity walletEntity = walletDAO.findByBill(bill_number);
        BigDecimal new_balance = walletEntity.getBalance().add(sum);
        isValidTopUp(new_balance);
        walletEntity.setBalance(new_balance);

        this.update(walletEntity);
    }

    @Override
    public List<WalletDTO> getSortedListByUserDTO(UserDTO userDTO, String sortBy) {
        UserEntity userEntity = userDAO.findByUsername(userDTO.getUsername());

        List<WalletEntity> walletEntityList = walletDAO.getSortedListByUserEntity(userEntity, sortBy);
        List<WalletDTO> walletDTOList = new ArrayList<>();
        for (WalletEntity walletEntity : walletEntityList) {
            walletDTOList.add(WalletMapper.INSTANCE.toDTO(walletEntity));
        }
        return walletDTOList;
    }

    @Override
    public List<WalletDTO> getSortedListByUserDTOAndState(UserDTO userDTO, WalletState state, String sortBy) {
        UserEntity userEntity = userDAO.findByUsername(userDTO.getUsername());

        List<WalletEntity> walletEntityList = walletDAO.getSortedListByUserEntityAndState(userEntity, state, sortBy);
        List<WalletDTO> walletDTOList = new ArrayList<>();
        for (WalletEntity walletEntity : walletEntityList) {
            walletDTOList.add(WalletMapper.INSTANCE.toDTO(walletEntity));
        }
        return walletDTOList;
    }

    @Override
    public void createWallet(UserDTO userDTO, String walletName) throws WalletCreationException {
        UserEntity userEntity = userDAO.findByUsername(userDTO.getUsername());
        BigDecimal balance = new BigDecimal("0.0");
        int billNumber;
        do {
            billNumber = Utils.generateBill();
        } while (walletDAO.existsByBill(billNumber));

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBill_number(billNumber);
        walletEntity.setUser_id(userEntity.getId());
        walletEntity.setState(WalletState.UNBLOCKED);
        walletEntity.setName(walletName);
        walletEntity.setBalance(balance);

        isValidCreate(userEntity, walletEntity);

        walletDAO.save(walletEntity);
    }

    @Override
    public TransferEntity doTransfer(TransferDTO transferDTO) {
        return walletDAO.doTransfer(transferDTO);
    }

    @Override
    public WalletEntity getWalletByBill(int bill_number) throws WalletNotFoundException{

        return getWalletEntityByBill(bill_number);
    }

    private WalletEntity getWalletEntityByBill(int bill_number) throws WalletNotFoundException {
        WalletEntity walletEntity = walletDAO.findByBill(bill_number);

        return walletEntity;
    }

    @Override
    public void blockWallet(int bill_number) throws WalletNotFoundException{
        WalletEntity walletEntity = getWalletEntityByBill(bill_number);
        walletEntity.setState(WalletState.BLOCKED);

        walletDAO.update(walletEntity);
    }

    @Override
    public void unblockWallet(int bill_number) throws WalletNotFoundException{
        WalletEntity walletEntity = getWalletEntityByBill(bill_number);

        walletEntity.setState(WalletState.UNBLOCKED);

        walletDAO.update(walletEntity);
    }

    private void isValidCreate(UserEntity userEntity, WalletEntity walletEntity) throws WalletCreationException {
        List<WalletEntity> walletEntityList = walletDAO.getSortedListByUserEntity(userEntity, SortConstants.DEFAULT_WALLET_SORT);

        if (!walletEntity.getName().equals(walletEntity.getName().trim())
                || walletEntity.getName().isBlank()){
            throw new WalletCreationException("alertError.whitespaces");
        }

        if(walletEntityList.size() >= MAX_WALLETS_COUNT){
            throw new WalletCreationException("alertError.max_wallets");
        }

        for (WalletEntity entity : walletEntityList) {
            if(entity.getName().equals(walletEntity.getName())){
                throw new WalletCreationException("alertError.wallet_name_exists");
            }
        }
    }

    private void isValidTopUp(BigDecimal balance) throws MaxBalanceException{
        if (balance.compareTo(MAX_BALANCE) > 0){
            throw new MaxBalanceException("alertError.max-top-up");
        }
    }

    private void update(WalletEntity entity) {
        walletDAO.update(entity);
    }

}

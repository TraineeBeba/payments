package com.epam.payments.core.service.wallet;

import com.epam.payments.core.model.dto.TransferDTO;
import com.epam.payments.core.model.dto.UserDTO;
import com.epam.payments.core.model.dto.WalletDTO;
import com.epam.payments.core.model.entity.TransferEntity;
import com.epam.payments.core.model.enums.state.WalletState;
import com.epam.payments.core.service.Service;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService extends Service {
    BigDecimal MAX_BALANCE = new BigDecimal(20_000.0);
    int MAX_WALLETS_COUNT = 3;
    void createWallet(UserDTO userDTO, String walletName) throws WalletCreationException;

    void topUpWallet(int bill_number, BigDecimal sum) throws MaxBalanceException;

    List<WalletDTO> getSortedListByUserDTO(UserDTO userDTO, String walletSort);

    List<WalletDTO> getSortedListByUserDTOAndState(UserDTO userDTO, WalletState state, String walletSort);

    WalletDTO getWalletByBill(int bill_number) throws WalletNotFoundException;

    void blockWallet(int bill_number) throws WalletNotFoundException;

    void unblockWallet(int bill_number) throws WalletNotFoundException;

    TransferEntity doTransfer(TransferDTO transferDTO);


}

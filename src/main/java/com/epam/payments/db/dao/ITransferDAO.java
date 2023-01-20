package com.epam.payments.db.dao;

import com.epam.payments.db.dto.TransferDTO;
import com.epam.payments.db.dto.WalletDTO;

public interface ITransferDAO {

    void createTranfer(TransferDTO transferDTO);


}

package com.epam.payments.core.service;

import com.epam.payments.core.model.entity.TransferEntity;

public interface TransferService extends Service{
    String isValidTransfer(TransferEntity transferEntity);
}

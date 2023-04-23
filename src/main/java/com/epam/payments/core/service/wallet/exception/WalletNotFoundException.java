package com.epam.payments.core.service.wallet.exception;

import com.epam.payments.core.service.exeption.ServiceException;

public class WalletNotFoundException extends ServiceException {
    private static final long serialVersionUID = -3508822186508513744L;

    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

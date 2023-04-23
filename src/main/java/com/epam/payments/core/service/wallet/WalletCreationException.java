package com.epam.payments.core.service.wallet;

import com.epam.payments.core.service.exeption.ServiceException;

public class WalletCreationException extends ServiceException {
    public WalletCreationException(String message) {
        super(message);
    }

    public WalletCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

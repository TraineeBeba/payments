package com.epam.payments.core.service.wallet;

import com.epam.payments.core.service.exeption.ServiceException;

public class MaxBalanceException extends ServiceException {
    public MaxBalanceException(String message) {
        super("" + message);
    }

    public MaxBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}

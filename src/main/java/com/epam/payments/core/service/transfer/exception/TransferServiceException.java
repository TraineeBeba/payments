package com.epam.payments.core.service.transfer.exception;

import com.epam.payments.core.service.exeption.ServiceException;

public class TransferServiceException extends ServiceException {
    public TransferServiceException(String message) {
        super(message);
    }

    public TransferServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

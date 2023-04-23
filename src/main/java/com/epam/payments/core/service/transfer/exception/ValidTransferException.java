package com.epam.payments.core.service.transfer.exception;

public class ValidTransferException extends TransferServiceException{
    public ValidTransferException(String message) {
        super(message);
    }

    public ValidTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}

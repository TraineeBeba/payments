package com.epam.payments.core.service.transfer.exception;

public class PrepareTransferException extends TransferServiceException{
    public PrepareTransferException(String message) {
        super(message);
    }

    public PrepareTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}

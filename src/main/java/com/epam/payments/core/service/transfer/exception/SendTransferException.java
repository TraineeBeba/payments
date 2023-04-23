package com.epam.payments.core.service.transfer.exception;

public class SendTransferException extends TransferServiceException{
    public SendTransferException(String message) {
        super(message);
    }

    public SendTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}

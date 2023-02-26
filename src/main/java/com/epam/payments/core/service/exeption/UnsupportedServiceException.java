package com.epam.payments.core.service.exeption;

import com.epam.payments.exeption.InternalServerException;

public class UnsupportedServiceException extends InternalServerException {
    private static final long serialVersionUID = -8723218544804062212L;

    public UnsupportedServiceException(String message) {
        super(message);
    }

    public UnsupportedServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

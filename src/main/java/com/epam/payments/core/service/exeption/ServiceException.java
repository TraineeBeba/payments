package com.epam.payments.core.service.exeption;

import com.epam.payments.exeption.InternalServerException;

public class ServiceException extends Exception{
    private static final long serialVersionUID = 3729550039046367793L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
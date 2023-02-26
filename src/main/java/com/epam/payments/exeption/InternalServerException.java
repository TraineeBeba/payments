package com.epam.payments.exeption;

import com.epam.payments.exeption.constant.InternalServerErrorMessages;

public class InternalServerException extends Exception implements InternalServerErrorMessages {
    private static final long serialVersionUID = -9215808911368965491L;

    public InternalServerException(String message) {
        super(INTERNAL_SERVER_ERROR + message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(INTERNAL_SERVER_ERROR + message, cause);
    }
}
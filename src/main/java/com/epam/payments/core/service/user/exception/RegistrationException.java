package com.epam.payments.core.service.user.exception;

public class RegistrationException extends UserServiceException {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
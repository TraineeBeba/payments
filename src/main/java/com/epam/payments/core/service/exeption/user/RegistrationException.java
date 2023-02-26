package com.epam.payments.core.service.exeption.user;

public class RegistrationException extends UserServiceException {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
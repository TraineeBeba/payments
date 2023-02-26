package com.epam.payments.core.service.exeption.user;

public class UserValidationException extends UserServiceException {
    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.epam.payments.core.service.user.exception;

public class UserValidationException extends UserServiceException {
    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
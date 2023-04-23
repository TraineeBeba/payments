package com.epam.payments.core.service.user.exception;

public class AuthenticationException extends UserServiceException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
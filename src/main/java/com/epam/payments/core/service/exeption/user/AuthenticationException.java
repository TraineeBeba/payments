package com.epam.payments.core.service.exeption.user;

public class AuthenticationException extends UserServiceException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
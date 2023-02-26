package com.epam.payments.core.utils.exeption;

import com.epam.payments.exeption.InternalServerException;

public class ParameterNotFoundException extends InternalServerException {
    public ParameterNotFoundException(String paramName) {
        super("Parameter not found: " + paramName);
    }

    public ParameterNotFoundException(String paramName, Throwable cause) {
        super("Parameter not found: " + paramName, cause);
    }
}


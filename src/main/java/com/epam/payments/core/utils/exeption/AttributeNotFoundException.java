package com.epam.payments.core.utils.exeption;

import com.epam.payments.exeption.InternalServerException;

public class AttributeNotFoundException extends InternalServerException {
    public AttributeNotFoundException(String paramName) {
        super("Parameter not found: " + paramName);
    }

    public AttributeNotFoundException(String paramName, Throwable cause) {
        super("Parameter not found: " + paramName, cause);
    }
}
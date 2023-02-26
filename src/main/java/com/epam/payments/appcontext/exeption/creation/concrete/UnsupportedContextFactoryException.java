package com.epam.payments.appcontext.exeption.creation.concrete;

import com.epam.payments.appcontext.exeption.constant.AppContextErrorMessages;
import com.epam.payments.appcontext.exeption.creation.AppContextCreationException;

public class UnsupportedContextFactoryException extends AppContextCreationException implements AppContextErrorMessages {
    public UnsupportedContextFactoryException(String message) {
        super(UNSUPPORTED_FACTORY_ERROR + message);
    }

    public UnsupportedContextFactoryException(String message, Throwable cause) {
        super(UNSUPPORTED_FACTORY_ERROR + message, cause);
    }
}

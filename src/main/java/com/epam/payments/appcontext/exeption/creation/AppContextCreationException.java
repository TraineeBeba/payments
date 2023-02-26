package com.epam.payments.appcontext.exeption.creation;

import com.epam.payments.appcontext.exeption.constant.AppContextErrorMessages;
import com.epam.payments.appcontext.exeption.GetAppContextException;

public class AppContextCreationException extends GetAppContextException implements AppContextErrorMessages {
    public AppContextCreationException(String message) {
        super(CREATE_APP_CONTEXT_ERROR + message);
    }

    public AppContextCreationException(String message, Throwable cause) {
        super(CREATE_APP_CONTEXT_ERROR + message, cause);
    }
}

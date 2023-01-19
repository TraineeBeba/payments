package com.epam.payments.command;

import com.epam.payments.command.result.CommandResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = -3044464540876000223L;

    public abstract CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}

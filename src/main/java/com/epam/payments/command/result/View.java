package com.epam.payments.command.result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface View {
    void render(CommandResult result, HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException;
}

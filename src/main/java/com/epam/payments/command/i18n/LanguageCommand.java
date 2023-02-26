package com.epam.payments.command.i18n;


import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ChangeLanguage")
public class LanguageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing LanguageCommand");
        HttpSession session = request.getSession();
        String language = getLanguageParameter(request);
        setAttributes(session, language);

        return new RedirectResult(request.getContextPath()+"?"+request.getParameter("url"));
    }

    private void setAttributes(HttpSession session, String language) {
        session.setAttribute("language", language);
    }

    private String getLanguageParameter(HttpServletRequest request) {
        String language = request.getParameter("language");
        LOG.trace("Language is switched to " + language);
        return language;
    }
}

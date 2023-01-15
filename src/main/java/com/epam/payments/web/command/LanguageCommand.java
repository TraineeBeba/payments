package com.epam.payments.web.command;

import com.epam.payments.Path;
import com.epam.payments.exception.Errors;
import com.epam.payments.web.command.factory.CommandFactory;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LanguageCommand");
        HttpSession session = request.getSession();
        List<String> languages = Arrays.asList("en","uk");
        String language = request.getParameter("language");
        boolean existLanguage = false;
        for(String lang: languages){
            if (language.equals(lang)){
                existLanguage = true;
            }
        }

        if (!existLanguage){
            request.setAttribute("errorMessage", Errors.ERR_INVALID_VALUE_LANGUAGE);
            return Path.PAGE_ERROR_PAGE;
        }else {
            session.setAttribute("language", language);
        }

        String toMove = "/controller?command=" + request.getParameter("goto");

        return toMove;
    }
}

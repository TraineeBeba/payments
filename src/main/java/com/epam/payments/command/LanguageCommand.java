package com.epam.payments.command;


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
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response, String forward) throws IOException, ServletException {
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
            LOG.info("ERROR");
//            request.setAttribute("errorMessage", Errors.ERR_INVALID_VALUE_LANGUAGE);
//            return Path.PAGE_ERROR_PAGE;
        }else {
            session.setAttribute("language", language);
        }

        LOG.info("Language is switched to " + language.toUpperCase());
        return new RedirectResult(request.getParameter("redirect"));
    }
}

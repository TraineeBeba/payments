package com.epam.payments.command.i18n;


import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.RedirectResult;
import com.epam.payments.core.utils.ServletUtils;
import com.epam.payments.exeption.InternalServerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.payments.command.constant.ParamNames.LANGUAGE;
import static com.epam.payments.command.constant.ParamNames.QUERY_PARAMS;
import static com.epam.payments.command.constant.WebUrlConstants.COMMON_URL_PREFIX;


public class LanguageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws InternalServerException {
        LOG.trace("Start tracing LanguageCommand");
        HttpSession session = request.getSession();

        String language = ServletUtils.getStringParameter(request, LANGUAGE);
        session.setAttribute(LANGUAGE, language);

        String queryParams = ServletUtils.getStringParameter(request, QUERY_PARAMS);
        String originalUrl = COMMON_URL_PREFIX + (queryParams != null ? "?" + queryParams : "");

        return new RedirectResult(originalUrl);
    }
}

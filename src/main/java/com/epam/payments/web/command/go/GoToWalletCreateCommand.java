package com.epam.payments.web.command.go;

import com.epam.payments.Path;
import com.epam.payments.db.dto.WalletDTO;
import com.epam.payments.web.command.Command;
import com.epam.payments.web.command.result.CommandResult;
import com.epam.payments.web.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToWalletCreateCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoToWalletCreateCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing GoToWalletCreateCommand");

        return new ForwardResult(Path.PAGE_CREATE_WALLET);
    }
}

package com.epam.payments.command.navigation.user;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoSendTransferCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoSendTransferCommand.class);
    private static final long serialVersionUID = 8040814109587733594L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

        return new ForwardResult(SEND_TRANSFER_PATH);
    }
}
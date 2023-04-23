package com.epam.payments.command.navigation.user.transfer;

import com.epam.payments.command.Command;
import com.epam.payments.command.result.CommandResult;
import com.epam.payments.command.result.ForwardResult;
import com.epam.payments.command.result.RedirectResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.payments.command.constant.WebPathConstants.USER_SEND_TRANSFER_PATH;


public class GoSendTransferCommand extends Command {
    public static final Logger LOG = Logger.getLogger(GoSendTransferCommand.class);
    private static final long serialVersionUID = 8040814109587733594L;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        LOG.trace("Start tracing GetCommand");

        return new ForwardResult(USER_SEND_TRANSFER_PATH);
    }
}
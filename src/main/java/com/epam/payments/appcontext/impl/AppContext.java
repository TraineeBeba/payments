package com.epam.payments.appcontext.impl;

import com.epam.payments.appcontext.constant.ContextConstants;
import com.epam.payments.core.service.*;
import com.epam.payments.core.service.exeption.UnsupportedServiceException;
import com.epam.payments.exeption.InternalServerException;

public abstract class AppContext implements ContextConstants {
    protected UserService userService;
    protected WalletService walletService;
    protected TransferService transferService;
    protected WalletRequestService walletRequestService;
    protected abstract UserService getUserService();
    protected abstract WalletService getWalletService() ;
    protected abstract TransferService getTransferService();
    protected abstract WalletRequestService getWalletRequestService();

    public <T extends Service> T getService(Class<T> serviceClass) throws InternalServerException {
        if (UserService.class.isAssignableFrom(serviceClass)) {
            return serviceClass.cast(getUserService());
        } else if (WalletService.class.isAssignableFrom(serviceClass)) {
            return serviceClass.cast(getWalletService());
        } else if (TransferService.class.isAssignableFrom(serviceClass)) {
            return serviceClass.cast(getTransferService());
        } else if (WalletRequestService.class.isAssignableFrom(serviceClass)) {
            return serviceClass.cast(getWalletRequestService());
        } else {
            throw new UnsupportedServiceException("Unknown service class: " + serviceClass.getName());
        }
    }
 }

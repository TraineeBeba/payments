package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.model.enums.type.WalletRequestType;

public class WalletRequestEntity {
    private Long id;
    private WalletEntity wallet;
    private RequestStatus status;
    private WalletRequestType type;

    public WalletRequestEntity() {
    }

    public WalletRequestEntity(Long id, WalletEntity wallet, RequestStatus status, WalletRequestType type) {
        this.id = id;
        this.wallet = wallet;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public WalletRequestType getType() {
        return type;
    }

    public void setType(WalletRequestType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WalletRequestEntity{" +
                "id=" + id +
                ", wallet=" + wallet +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

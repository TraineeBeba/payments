package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.entity.WalletEntity;
import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.model.enums.type.WalletRequestType;

import java.util.Objects;

public class WalletRequestDTO {
    private WalletDTO wallet;
    private RequestStatus status;
    private WalletRequestType type;

    public WalletRequestDTO(WalletDTO wallet, RequestStatus status, WalletRequestType type) {
        this.wallet = wallet;
        this.status = status;
        this.type = type;
    }

    public WalletDTO getWallet() {
        return wallet;
    }

    public void setWallet(WalletDTO wallet) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletRequestDTO)) return false;
        WalletRequestDTO that = (WalletRequestDTO) o;
        return Objects.equals(wallet, that.wallet) && status == that.status && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallet, status, type);
    }

    @Override
    public String toString() {
        return "WalletRequestDTO{" +
                "wallet=" + wallet +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

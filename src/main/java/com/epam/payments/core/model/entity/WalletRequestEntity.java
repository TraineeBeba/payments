package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.status.RequestStatus;
import com.epam.payments.core.model.enums.type.WalletRequestType;

import java.util.Objects;

public class WalletRequestEntity {
    private Long id;
    private Long wallet_id;
    private RequestStatus status;
    private WalletRequestType type;

    public WalletRequestEntity() {
    }

    public WalletRequestEntity(Long id, Long wallet_id, RequestStatus status, WalletRequestType type) {
        this.id = id;
        this.wallet_id = wallet_id;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Long wallet_id) {
        this.wallet_id = wallet_id;
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
        if (!(o instanceof WalletRequestEntity)) return false;
        WalletRequestEntity that = (WalletRequestEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(wallet_id, that.wallet_id) && status == that.status && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wallet_id, status, type);
    }

    @Override
    public String toString() {
        return "WalletRequestEntity{" +
                "id=" + id +
                ", wallet_id=" + wallet_id +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

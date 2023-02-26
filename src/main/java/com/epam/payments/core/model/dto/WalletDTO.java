package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletDTO {
    private UserEntity user;
    private WalletDTO state;
    private String name;
    private int bill_number;
    private BigDecimal balance;

    public WalletDTO(UserEntity user, WalletDTO state, String name, int bill_number, BigDecimal balance) {
        this.user = user;
        this.state = state;
        this.name = name;
        this.bill_number = bill_number;
        this.balance = balance;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public WalletDTO getState() {
        return state;
    }

    public void setState(WalletDTO state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBill_number() {
        return bill_number;
    }

    public void setBill_number(int bill_number) {
        this.bill_number = bill_number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletDTO)) return false;
        WalletDTO walletDTO = (WalletDTO) o;
        return bill_number == walletDTO.bill_number && Objects.equals(user, walletDTO.user) && Objects.equals(state, walletDTO.state) && Objects.equals(name, walletDTO.name) && Objects.equals(balance, walletDTO.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, state, name, bill_number, balance);
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "user=" + user +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", bill_number=" + bill_number +
                ", balance=" + balance +
                '}';
    }
}

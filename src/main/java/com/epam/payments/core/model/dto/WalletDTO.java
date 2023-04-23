package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.entity.UserEntity;
import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletDTO {
    private Long user_id;
    private WalletState state;
    private String name;
    private int bill_number;
    private BigDecimal balance;

    public WalletDTO(Long user_id, WalletState state, String name, int bill_number, BigDecimal balance) {
        this.user_id = user_id;
        this.state = state;
        this.name = name;
        this.bill_number = bill_number;
        this.balance = balance;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public WalletState getState() {
        return state;
    }

    public void setState(WalletState state) {
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
        return bill_number == walletDTO.bill_number && Objects.equals(user_id, walletDTO.user_id) && state == walletDTO.state && Objects.equals(name, walletDTO.name) && Objects.equals(balance, walletDTO.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, state, name, bill_number, balance);
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "user_id=" + user_id +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", bill_number=" + bill_number +
                ", balance=" + balance +
                '}';
    }
}

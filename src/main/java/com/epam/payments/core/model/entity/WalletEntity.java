package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;

public class WalletEntity {
    private Long id;
    private UserEntity user;
    private WalletState state;
    private String name;
    private int bill_number;
    private BigDecimal balance;

    public WalletEntity() {
    }

    public WalletEntity(Long id, UserEntity user, WalletState state, String name, int bill_number, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.state = state;
        this.name = name;
        this.bill_number = bill_number;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", user=" + user +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", bill_number=" + bill_number +
                ", balance=" + balance.toString() +
                '}';
    }
}

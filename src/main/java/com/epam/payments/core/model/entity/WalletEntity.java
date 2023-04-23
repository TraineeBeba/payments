package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.state.WalletState;

import java.math.BigDecimal;

public class WalletEntity {
    private Long id;
    private Long user_id;
    private WalletState state;
    private String name;
    private int bill_number;
    private BigDecimal balance;

    public WalletEntity() {
    }

    public WalletEntity(Long id, Long user_id, WalletState state, String name, int bill_number, BigDecimal balance) {
        this.id = id;
        this.user_id = user_id;
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
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", user=" + user_id +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", bill_number=" + bill_number +
                ", balance=" + balance.toString() +
                '}';
    }
}

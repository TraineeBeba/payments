package com.epam.payments.db.dto;

import java.io.Serializable;

public class WalletDTO implements Serializable {
    private static final long serialVersionUID = 7855902366124627796L;
    
    private Long id;
    private Long user_id;
    private Long state_id = 1L;
    private int bill_number;
    private double balance;

    public WalletDTO() {
    }

    public WalletDTO(Long id, Long user_id, Long state_id, int bill_number, double balance) {
        this.id = id;
        this.user_id = user_id;
        this.state_id = state_id;
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

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public int getBill_number() {
        return bill_number;
    }

    public void setBill_number(int bill_number) {
        this.bill_number = bill_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", state_id=" + state_id +
                ", bill_number=" + bill_number +
                ", balance=" + balance +
                '}';
    }
}

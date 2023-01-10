package com.epam.payments.db.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TransferDTO implements Serializable {
    private static final long serialVersionUID = 2004384289185509242L;
    
    private Long id;
    private Long sender_wallet_id;
    private int recipient_bill_number;
    private LocalDateTime date_time;
    private double sum;

    public TransferDTO() {
    }

    public TransferDTO(Long id, Long sender_wallet_id, int recipient_bill_number, LocalDateTime date_time, double sum) {
        this.id = id;
        this.sender_wallet_id = sender_wallet_id;
        this.recipient_bill_number = recipient_bill_number;
        this.date_time = date_time;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender_wallet_id() {
        return sender_wallet_id;
    }

    public void setSender_wallet_id(Long sender_wallet_id) {
        this.sender_wallet_id = sender_wallet_id;
    }

    public int getRecipient_bill_number() {
        return recipient_bill_number;
    }

    public void setRecipient_bill_number(int recipient_bill_number) {
        this.recipient_bill_number = recipient_bill_number;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "id=" + id +
                ", sender_wallet_id=" + sender_wallet_id +
                ", recipient_bill_number=" + recipient_bill_number +
                ", date_time=" + date_time +
                ", sum=" + sum +
                '}';
    }
}

package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.status.TransferStatus;

import java.sql.Date;

public class TransferEntity {
    private Long id;
    private TransferStatus status;
    private int sender_bill_number;
    private int recipient_bill_number;
    private Date date;
    private double sum;

    public TransferEntity() {
    }

    public TransferEntity(Long id, TransferStatus status, int sender_bill_number, int recipient_bill_number, Date date, double sum) {
        this.id = id;
        this.status = status;
        this.sender_bill_number = sender_bill_number;
        this.recipient_bill_number = recipient_bill_number;
        this.date = date;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public int getSender_bill_number() {
        return sender_bill_number;
    }

    public void setSender_bill_number(int sender_bill_number) {
        this.sender_bill_number = sender_bill_number;
    }

    public int getRecipient_bill_number() {
        return recipient_bill_number;
    }

    public void setRecipient_bill_number(int recipient_bill_number) {
        this.recipient_bill_number = recipient_bill_number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TransferEntity{" +
                "id=" + id +
                ", status=" + status +
                ", sender_bill_number=" + sender_bill_number +
                ", recipient_bill_number=" + recipient_bill_number +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }
}

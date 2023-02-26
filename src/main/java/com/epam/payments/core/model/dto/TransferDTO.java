package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.enums.status.TransferStatus;

import java.sql.Date;
import java.util.Objects;

public class TransferDTO {
    private TransferStatus status;
    private int sender_bill_number;
    private int recipient_bill_number;
    private Date date;
    private double sum;

    public TransferDTO(TransferStatus status, int sender_bill_number, int recipient_bill_number, Date date, double sum) {
        this.status = status;
        this.sender_bill_number = sender_bill_number;
        this.recipient_bill_number = recipient_bill_number;
        this.date = date;
        this.sum = sum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferDTO)) return false;
        TransferDTO that = (TransferDTO) o;
        return sender_bill_number == that.sender_bill_number && recipient_bill_number == that.recipient_bill_number && Double.compare(that.sum, sum) == 0 && status == that.status && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, sender_bill_number, recipient_bill_number, date, sum);
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
                "status=" + status +
                ", sender_bill_number=" + sender_bill_number +
                ", recipient_bill_number=" + recipient_bill_number +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }
}

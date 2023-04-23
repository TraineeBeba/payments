package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.enums.status.TransferStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class TransferDTO {
    private TransferStatus status;
    private int sender_bill_number;
    private int recipient_bill_number;
    private Date date;
    private BigDecimal sum;

    public TransferDTO() {
    }

    public TransferDTO(TransferStatus status, int sender_bill_number, int recipient_bill_number, Date date, BigDecimal sum) {
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferDTO)) return false;
        TransferDTO that = (TransferDTO) o;
        return sender_bill_number == that.sender_bill_number && recipient_bill_number == that.recipient_bill_number && status == that.status && Objects.equals(date, that.date) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, sender_bill_number, recipient_bill_number, date, sum);
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

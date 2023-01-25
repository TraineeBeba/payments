package com.epam.payments.db.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.Date;

public class TransferDTO implements Serializable {
    private static final long serialVersionUID = 2004384289185509242L;
    
    private Long id;
    private Long status_id;
    private int sender_bill_number;
    private int recipient_bill_number;
    private Date date;
    private double sum;

    public TransferDTO() {
    }



    public TransferDTO(Long status_id, int sender_bill_number, int recipient_bill_number, double sum, Date date) {
        this.status_id = status_id;
        this.sender_bill_number = sender_bill_number;
        this.recipient_bill_number = recipient_bill_number;
        this.sum = sum;
        this.date = date;
    }

    public TransferDTO(Long id, int sender_bill_number, int recipient_bill_number, double sum) {
        this.id = id;
        this.sender_bill_number = sender_bill_number;
        this.recipient_bill_number = recipient_bill_number;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDate(java.util.Date date) {
        this.date = new Date(date.getTime());
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
                ", sender_bill_number=" + sender_bill_number +
                ", recipient_bill_number=" + recipient_bill_number +
                ", date_time=" + date +
                ", sum=" + sum +
                '}';
    }
}

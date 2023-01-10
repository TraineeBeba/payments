package com.epam.payments.db.dto;

import java.io.Serializable;

public class RequestToAdminDTO implements Serializable {
    private static final long serialVersionUID = 4886550632350807100L;
    
    private Long id;
    private Long req_type_id;
    private Long answer_type_id;
    private int bill_number;

    public RequestToAdminDTO() {
    }

    public RequestToAdminDTO(Long id, Long req_type_id, Long answer_type_id, int bill_number) {
        this.id = id;
        this.req_type_id = req_type_id;
        this.answer_type_id = answer_type_id;
        this.bill_number = bill_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReq_type_id() {
        return req_type_id;
    }

    public void setReq_type_id(Long req_type_id) {
        this.req_type_id = req_type_id;
    }

    public Long getAnswer_type_id() {
        return answer_type_id;
    }

    public void setAnswer_type_id(Long answer_type_id) {
        this.answer_type_id = answer_type_id;
    }

    public int getBill_number() {
        return bill_number;
    }

    public void setBill_number(int bill_number) {
        this.bill_number = bill_number;
    }

    @Override
    public String toString() {
        return "RequestToAdminDTO{" +
                "id=" + id +
                ", req_type_id=" + req_type_id +
                ", answer_type_id=" + answer_type_id +
                ", bill_number=" + bill_number +
                '}';
    }
}

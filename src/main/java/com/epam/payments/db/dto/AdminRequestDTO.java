package com.epam.payments.db.dto;

import java.io.Serializable;

public class AdminRequestDTO implements Serializable {

    private static final long serialVersionUID = -7063530625633845599L;

    private Long admin_id;
    private Long request_id;

    public AdminRequestDTO() {
    }

    public AdminRequestDTO(Long admin_id, Long request_id) {
        this.admin_id = admin_id;
        this.request_id = request_id;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Long request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "AdminRequestDTO{" +
                "admin_id=" + admin_id +
                ", request_id=" + request_id +
                '}';
    }
}

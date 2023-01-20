package com.epam.payments.db.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -102382045468275823L;
    
    private Long id;
    private Long role_id = 1L;
    private Long status_id = 1L;
    private String username;
    private String password;

    public UserDTO(){}

    public UserDTO(Long id, Long role_id, Long status_id, String username, String password) {
        this.id = id;
        this.role_id = role_id;
        this.status_id = status_id;
        this.username = username;
        this.password = password;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", state_id=" + status_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

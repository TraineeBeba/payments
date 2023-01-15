package com.epam.payments.db.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -102382045468275823L;
    
    private Long id;
    private Long role_id = 1L;
    private Long state_id = 1L;
    private String username;
    private String password;

    public UserDTO(){}

    public UserDTO(Long id, Long role_id, Long state_id, String username, String password) {
        this.id = id;
        this.role_id = role_id;
        this.state_id = state_id;
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

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
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
                ", state_id=" + state_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

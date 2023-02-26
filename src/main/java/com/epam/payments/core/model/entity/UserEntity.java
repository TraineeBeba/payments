package com.epam.payments.core.model.entity;

import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.model.enums.state.UserState;

public class UserEntity {
    private Long id;
    private Role role;
    private UserState state;
    private String username;
    private String password;

    public UserEntity() {
    }

    public UserEntity(Long id, Role role, UserState state, String username, String password) {
        this.id = id;
        this.role = role;
        this.state = state;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
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
        return "UserEntity{" +
                "id=" + id +
                ", role=" + role +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

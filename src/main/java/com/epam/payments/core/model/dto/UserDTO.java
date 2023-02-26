package com.epam.payments.core.model.dto;

import com.epam.payments.core.model.enums.role.Role;
import com.epam.payments.core.model.enums.state.UserState;

import java.util.Objects;

public class UserDTO {
    private Role role;
    private UserState state;
    private String username;
    private String password;

    public UserDTO(Role role, UserState state, String username, String password) {
        this.role = role;
        this.state = state;
        this.username = username;
        this.password = password;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return role == userDTO.role && state == userDTO.state && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, state, username, password);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "role=" + role +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

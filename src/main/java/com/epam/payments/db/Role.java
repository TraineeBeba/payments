package com.epam.payments.db;

import com.epam.payments.db.dto.UserDTO;

public enum Role {
    ROLE_USER, ROLE_ADMIN;

    public static Role getRole(UserDTO user) {
        Long roleId = user.getRole_id();
        return Role.values()[roleId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

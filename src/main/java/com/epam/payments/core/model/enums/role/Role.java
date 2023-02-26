package com.epam.payments.core.model.enums.role;

public enum Role {
    ROLE_USER(1L), ROLE_ADMIN(2L);

    Long id;

    Role(Long id) {
        this.id = id;
    }

    public static Role getRole(Long role_id) {
        return Role.values()[role_id.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

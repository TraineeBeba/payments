package com.epam.payments.db;

public enum UserStatus {
    UNBLOCKED, BLOCKED;

    public static UserStatus getState(Long stateId) {
        return UserStatus.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

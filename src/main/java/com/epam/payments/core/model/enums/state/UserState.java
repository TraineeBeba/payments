package com.epam.payments.core.model.enums.state;

public enum UserState {
    UNBLOCKED(1L), BLOCKED(2L);

    Long id;

    UserState(Long id) {
        this.id = id;
    }

    public static UserState getState(Long stateId) {
        return UserState.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

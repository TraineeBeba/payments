package com.epam.payments.db;

import com.epam.payments.db.dto.UserDTO;

public enum State {
    UNBLOCKED, BLOCKED;

    public static State getState(Long stateId) {
        return State.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

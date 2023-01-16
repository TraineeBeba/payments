package com.epam.payments.db;

import com.epam.payments.db.dto.UserDTO;

public enum State {
    UNBLOCKED, BLOCKED;

    public static State getState(UserDTO user) {
        Long stateId = user.getState_id();
        return State.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

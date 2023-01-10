package com.epam.payments.db;

public enum State {
    UNBLOCKED, BLOCKED;

//    public static Role getState(UserDTO user) {
//        int stateId = user.getStateId();
//        return Role.values()[stateId];
//    }

    public String getName() {
        return name().toLowerCase();
    }
}

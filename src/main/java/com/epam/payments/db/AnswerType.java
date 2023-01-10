package com.epam.payments.db;

public enum AnswerType {
    AFFIRMATIVE, NEGATIVE;

//    public static Role getRole(UserDTO user) {
//        int roleId = user.getRoleId();
//        return Role.values()[roleId];
//    }

    public String getName() {
        return name().toLowerCase();
    }
}

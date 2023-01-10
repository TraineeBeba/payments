package com.epam.payments.db;

public enum RequestType {
    BLOCK_USER, UNBLOCK_ADMIN, BLOCK_WALLET, UNBLOCK_WALLET;

//    public static Role getRole(UserDTO user) {
//        int roleId = user.getRoleId();
//        return Role.values()[roleId];
//    }

    public String getName() {
        return name().toLowerCase();
    }
}

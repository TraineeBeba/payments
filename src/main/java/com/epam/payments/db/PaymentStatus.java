package com.epam.payments.db;

public enum PaymentStatus {
    PREPARED, SENT;

    public static PaymentStatus getState(Long stateId) {
        return PaymentStatus.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

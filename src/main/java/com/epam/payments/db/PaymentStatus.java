package com.epam.payments.db;

public enum PaymentStatus {
    PREPARED(1L), SENT(2L);

    Long id;

    PaymentStatus(Long id) {
        this.id = id;
    }

    public static PaymentStatus getState(Long stateId) {
        return PaymentStatus.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }
    public Long getId() {
        return id;
    }
}

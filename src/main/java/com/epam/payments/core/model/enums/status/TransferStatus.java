package com.epam.payments.core.model.enums.status;

public enum TransferStatus {
    PREPARED(1L), SENT(2L);

    Long id;

    TransferStatus(Long id) {
        this.id = id;
    }

    public static TransferStatus getState(Long stateId) {
        return TransferStatus.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

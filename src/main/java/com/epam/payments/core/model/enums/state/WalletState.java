package com.epam.payments.core.model.enums.state;

public enum WalletState {
    UNBLOCKED(1L), BLOCKED(2L);

    Long id;

    WalletState(Long id) {
        this.id = id;
    }

    public static WalletState getState(Long stateId) {
        return WalletState.values()[stateId.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

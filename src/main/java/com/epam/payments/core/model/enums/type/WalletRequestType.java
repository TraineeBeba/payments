package com.epam.payments.core.model.enums.type;

public enum WalletRequestType {
    UNBLOCK_WALLET(1L);

    Long id;

    WalletRequestType(Long id) {
        this.id = id;
    }

    public static WalletRequestType getType(Long request_id) {
        return WalletRequestType.values()[request_id.intValue() - 1];
    }

    public static WalletRequestType getType(String type) {
        for (WalletRequestType value : values()) {
            if (value.getName().equals(type)) return value;
        }
        return null;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

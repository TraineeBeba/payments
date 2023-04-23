package com.epam.payments.core.model.enums.status;

public enum RequestStatus {
    IN_PROCESS(1L), AFFIRMATIVE(2L), NEGATIVE(3L);

    Long id;

    RequestStatus(Long id) {
        this.id = id;
    }

    public static RequestStatus getRequestStatus(Long id) {
        return RequestStatus.values()[id.intValue() - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Long getId() {
        return id;
    }
}

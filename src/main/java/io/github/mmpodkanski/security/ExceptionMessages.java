package io.github.mmpodkanski.security;

public enum ExceptionMessages {
    OBJECT_NOT_FOUND("Object with that name not exists!"),
    RESERVATION_EXISTS("Reservation with that object already exists!"),
    LESSOR_NOT_EXISTS("Lessor with that id not exists!"),
    TENANT_NOT_EXISTS("Tenant with that id not exists!");

    private final String message;

    ExceptionMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

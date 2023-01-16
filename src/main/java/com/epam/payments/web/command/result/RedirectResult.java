package com.epam.payments.web.command.result;

public class RedirectResult implements CommandResult {
    private String refirectResource;

    public RedirectResult(String resource) {
        this.refirectResource = resource;
    }
    public String getResource() {
        return refirectResource;
    }
}

package com.epam.payments.web.command.result;

public class ForwardResult implements CommandResult {
    private String forwardResource;

    public ForwardResult(String resource) {
        this.forwardResource = resource;
    }
    public String getResource() {
        return forwardResource;
    }
}

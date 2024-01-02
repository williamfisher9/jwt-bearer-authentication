package com.apps.securityapp.enums;

public enum ResponseType {
    SUCCESS("SUCCESS", 1), ERROR("ERROR", 0);

    private String description;
    private int code;

    ResponseType(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}

package com.apps.securityapp.dto;

public class ResponseMessageDTO {
    private String response;

    public ResponseMessageDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

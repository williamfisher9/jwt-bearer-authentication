package com.apps.securityapp.dto;

import com.apps.securityapp.enums.ResponseType;

import java.time.LocalDateTime;

public class ResponseMessageDTO {
    private ResponseType responseType;
    private int httpStatusCode;
    private String httpStatusDescription;
    private LocalDateTime responseDateTime;
    private Object response;

    public ResponseMessageDTO() {
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getHttpStatusDescription() {
        return httpStatusDescription;
    }

    public void setHttpStatusDescription(String httpStatusDescription) {
        this.httpStatusDescription = httpStatusDescription;
    }

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(LocalDateTime responseDateTime) {
        this.responseDateTime = responseDateTime;
    }
}

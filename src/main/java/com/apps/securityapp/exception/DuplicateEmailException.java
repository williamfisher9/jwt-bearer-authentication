package com.apps.securityapp.exception;

public class DuplicateEmailException extends RuntimeException implements DuplicateRecordException{
    public DuplicateEmailException(String message){
        super(message);
    }


    @Override
    public String getExceptionMessage() {
        return this.getMessage();
    }
}

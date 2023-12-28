package com.apps.securityapp.exception;

public class DuplicateUsernameException extends RuntimeException implements DuplicateRecordException{
    public DuplicateUsernameException(String message){
        super(message);
    }


    @Override
    public String getExceptionMessage() {
        return this.getMessage();
    }
}

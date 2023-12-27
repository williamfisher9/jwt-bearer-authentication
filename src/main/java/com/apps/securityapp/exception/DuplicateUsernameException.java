package com.apps.securityapp.exception;

public class DuplicateUsernameException extends RuntimeException implements DuplicateRecordException{
    public DuplicateUsernameException(String message){
        super(message);
    }


    @Override
    public String getCustomMessage() {
        return this.getMessage();
    }
}

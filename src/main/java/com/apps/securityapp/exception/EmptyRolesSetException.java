package com.apps.securityapp.exception;

public class EmptyRolesSetException extends RuntimeException implements RoleException{
    public EmptyRolesSetException(String message){
        super(message);
    }

    @Override
    public String getExceptionMessage() {
        return this.getMessage();
    }
}

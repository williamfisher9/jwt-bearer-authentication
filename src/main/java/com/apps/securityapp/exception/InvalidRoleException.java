package com.apps.securityapp.exception;

public class InvalidRoleException extends RuntimeException implements RolesException{
    public InvalidRoleException(String message){
        super(message);
    }

    @Override
    public String getExceptionMessage() {
        return this.getMessage();
    }
}

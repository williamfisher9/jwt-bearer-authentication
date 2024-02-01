package com.apps.securityapp.exception;

public class InvalidRoleException extends RuntimeException implements RoleException{
    public InvalidRoleException(String message){
        super(message);
    }

    @Override
    public String getExceptionMessage() {
        return this.getMessage();
    }
}

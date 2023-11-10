package com.auth.exceptions.user;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("Login already exists!");
    }
}

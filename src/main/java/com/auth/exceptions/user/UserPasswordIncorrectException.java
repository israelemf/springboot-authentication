package com.auth.exceptions.user;

public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException() {
        super("User password incorrect!");
    }
}

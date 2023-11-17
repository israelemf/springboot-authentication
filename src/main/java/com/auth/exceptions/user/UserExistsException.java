package com.auth.exceptions.user;

import com.auth.exceptions.GlobalException;

public class UserExistsException extends GlobalException {
    public UserExistsException(String campo) {
        super("Login '%s' already exists!", campo);
    }
}

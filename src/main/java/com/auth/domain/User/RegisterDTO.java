package com.auth.domain.User;

import com.auth.enums.UserRole;

import java.util.Objects;

public class RegisterDTO {
    private String login;
    private String password;
    private UserRole role;

    public RegisterDTO(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

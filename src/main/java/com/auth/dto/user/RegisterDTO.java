package com.auth.dto.user;

import com.auth.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {
    @NotBlank(message = "Login is required!")
    private String login;
    @NotBlank(message = "Password is required!")
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

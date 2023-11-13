package com.auth.dto.user;

import com.auth.enums.RoleName;
import jakarta.validation.constraints.NotBlank;

public class RegisterUserDto {
    @NotBlank(message = "Login is required!")
    private final String login;
    @NotBlank(message = "Password is required!")
    private final String password;
    private final RoleName role;

    public RegisterUserDto(String login, String password, RoleName role) {
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

    public RoleName getRole() {
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

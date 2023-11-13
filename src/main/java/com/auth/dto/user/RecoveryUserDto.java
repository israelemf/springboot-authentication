package com.auth.dto.user;

import com.auth.entities.Role;

import java.util.List;

public class RecoveryUserDto {
    private Long id;
    private String login;
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

package com.auth.dto.user;

import java.util.Objects;

public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginResponseDTO that)) return false;
        return Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}

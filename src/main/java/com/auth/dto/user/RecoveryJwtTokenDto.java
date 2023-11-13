package com.auth.dto.user;

import java.util.Objects;

public class RecoveryJwtTokenDto {
    private String token;

    public RecoveryJwtTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecoveryJwtTokenDto that)) return false;
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

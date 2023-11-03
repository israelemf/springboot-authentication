package com.auth.services;

import com.auth.configuration.security.TokenService;
import com.auth.dto.user.AuthenticationDTO;
import com.auth.dto.user.LoginResponseDTO;
import com.auth.dto.user.RegisterDTO;
import com.auth.entities.User;
import com.auth.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(AuthenticationDTO data) {
        return this.tokenService.generateToken(data.getLogin());
    }

    public void register(RegisterDTO data) {
        String encryptedPassword = this.bCryptPasswordEncoder.encode(data.getPassword());
        User user = new User(data.getLogin(), encryptedPassword, data.getRole());

        this.repository.save(user);
    }
}

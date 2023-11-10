package com.auth.controllers;

import com.auth.configuration.security.TokenService;
import com.auth.dto.user.AuthenticationDTO;
import com.auth.dto.user.LoginResponseDTO;
import com.auth.dto.user.RegisterDTO;
import com.auth.entities.User;
import com.auth.repository.UserRepository;
import com.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        return ResponseEntity.ok(new LoginResponseDTO(this.service.login(data)));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data) {
        this.service.register(data);

        return ResponseEntity.ok().build();
    }
}

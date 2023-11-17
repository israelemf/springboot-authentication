package com.auth.controllers;

import com.auth.dto.user.LoginUserDto;
import com.auth.dto.user.RecoveryJwtTokenDto;
import com.auth.dto.user.RegisterUserDto;
import com.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> login(@RequestBody @Valid LoginUserDto data) {
        return ResponseEntity.ok(this.service.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> register(@RequestBody @Valid RegisterUserDto data) {
        this.service.register(data);

        return ResponseEntity.ok().build();
    }
}

package com.auth.services;

import com.auth.configuration.security.authentication.JwtTokenService;
import com.auth.configuration.security.userdetails.UserDetailsImpl;
import com.auth.dto.user.LoginUserDto;
import com.auth.dto.user.RecoveryJwtTokenDto;
import com.auth.dto.user.RegisterUserDto;
import com.auth.entities.Role;
import com.auth.entities.User;
import com.auth.exceptions.user.UserExistsException;
import com.auth.exceptions.user.UserNotFoundException;
import com.auth.exceptions.user.UserPasswordIncorrectException;
import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository repository;
    @Autowired
    JwtTokenService jwtTokenService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public RecoveryJwtTokenDto login(LoginUserDto data) {
        String login = data.getLogin();
        String password = data.getPassword();

        if (this.repository.findByLogin(data.getLogin()) == null) {
            throw new UserNotFoundException();
        }

        if (!this.bCryptPasswordEncoder.matches(password, this.repository.getPassword(data.getLogin()))) {
            throw new UserPasswordIncorrectException();
        }

        // Cria um objeto de autenticação com email e senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login, password);

        // Autentica o usuário com o objeto de autenticação
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém os detalhes do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        // Gera um token para o usuário autenticado
        return new RecoveryJwtTokenDto(this.jwtTokenService.generateToken(userDetails));
    }

    public void register(RegisterUserDto data) {
        String encryptedPassword = this.bCryptPasswordEncoder.encode(data.getPassword());

        if (this.repository.findByLogin(data.getLogin()) != null) {
            throw new UserExistsException();
        }

        User user = new User(data.getLogin(), encryptedPassword, List.of(new Role(data.getRole())));
        this.repository.save(user);
    }
}

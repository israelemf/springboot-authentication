package com.auth.services;

import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// Para que o Spring Security identifique a classe de autorização, temos que implementar a UserDetailsService
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.repository.findByLogin(login);
    }
}

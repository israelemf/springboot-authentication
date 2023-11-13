package com.auth.configuration.security.userdetails;

import com.auth.entities.User;
import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /*
     O método loadUserByUsername da interface UserDetailsService, é utilizado para carregar os detalhes do usuário,
     com base na credencial de login fornecida, o método é chamado automaticamente pelo Spring durante o processo
     de autenticação, e é responsável por retornar um UserDetails com base na credencial fornecida.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new UserDetailsImpl(user);
    }
}

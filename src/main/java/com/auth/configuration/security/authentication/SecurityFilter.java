package com.auth.configuration.security.authentication;

import com.auth.configuration.security.authentication.JwtTokenService;
import com.auth.configuration.security.config.SecurityConfiguration;
import com.auth.configuration.security.userdetails.UserDetailsImpl;
import com.auth.entities.User;
import com.auth.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointRequiresAuthentication(request)) {
            String token = this.recoverToken(request);
            if (token != null) {
                String subject = jwtTokenService.validateToken(token);
                User user = userRepository.findByLogin(subject); // Busca o usuário pelo login (Que é o assunto do token)
                UserDetailsImpl userDetails = new UserDetailsImpl(user); // Cria os detalhes do usuário encontrado

                // Cria um objeto de autenticação do Spring Security
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                // Define o objeto de autenticação no contexto de segurança do Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("The token is missing!");
            }
        }

        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            return null;
        }

        return authorizationHeader.replace("Bearer ", "");
    }

    // Verifica se o endpoint necessita de autenticação por token
    private boolean checkIfEndpointRequiresAuthentication(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_NOT_REQUIRING_AUTHENTICATION).contains(requestURI);
    }
}

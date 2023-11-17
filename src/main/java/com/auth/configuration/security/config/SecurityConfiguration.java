package com.auth.configuration.security.config;

import com.auth.configuration.security.authentication.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.NoSuchAlgorithmException;

// Classe para indicar que estamos desabilitando as configurações padrões do Spring Security e implementando nossas próprias
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    public static final String [] ENDPOINTS_NOT_REQUIRING_AUTHENTICATION = {
            "/auth/login",
            "/auth/register"
    };

    public static final String [] ENDPOINTS_WITH_REQUIRING_AUTHENTICATION = {
            "/users",
            "/store/products"
    };

    public static final String [] ENDPOINTS_ADMIN = {
            "/store/products/add"
    };

    @Bean
    // SecurityFilterChain será conjunto de filtros que vamos aplicar nas nossas requisições, validando as autenticações
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(ENDPOINTS_WITH_REQUIRING_AUTHENTICATION).authenticated()
                        .requestMatchers(ENDPOINTS_NOT_REQUIRING_AUTHENTICATION).permitAll()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
                        .anyRequest().denyAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .build();

        // Stateful: Armazenamos as informações das sessões dos usuários
        // Stateless: Autenticação via Token, após validar usuário e senha (padrão REST)
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Classe que o Spring Security fornece para realizar a criptografia das senhas, utilizando algoritmo de HASH
        return new BCryptPasswordEncoder();
    }
}

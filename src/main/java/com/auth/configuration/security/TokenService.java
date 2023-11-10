package com.auth.configuration.security;

import com.auth.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    /* Como a autenticação é Stateless (não armazenamos a sessão ativa), guardamos todas essas informações no token,
       e esse Token transita entre o cliente-servidor
    */
    public String generateToken(String login) {
        try {
            // Recebe por parâmetro uma Secret, o que faz com que os Hash's sejam únicos.
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("store-auth")
                    .withSubject(login)
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token ", exception);
        }
    }

    // Após a criação do token na primeira requisição, é necessário validar se o token ainda é válido
    public String validateToken(String token) {
        try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                   .withIssuer("store-auth")
                   .build()
                   .verify(token)
                   .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}

package com.auth.configuration.security.authentication;

import com.auth.configuration.security.userdetails.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class JwtTokenService {
    @Value("${api.security.token.secret}")
    private String SECRET_KEY; // Chave secreta para gerar e verificar o token
    private static final String ISSUER = "store-auth"; // Emissor do token

    /* Como a autenticação é Stateless (não armazenamos a sessão ativa), guardamos todas essas informações no token,
       e esse Token transita entre o cliente-servidor
    */
    public String generateToken(UserDetailsImpl user) {
        try {
            // Recebe por parâmetro uma Secret, o que faz com que os Hash's sejam únicos.
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER) // Define o emissor do token
                    .withIssuedAt(tokenCreationDate()) // Define a data de emissão do token
                    .withExpiresAt(tokenExpirationDate()) // Define a data de expiração do token
                    .withSubject(user.getUsername()) // Define o assunto do token, no caso, o login do usuário
                    .sign(algorithm); // Assina o token utilizando o algoritmo escolhido acima
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token!", exception);
        }
    }

    // Após a criação do token na primeira requisição, é necessário validar se o token ainda é válido
    public String validateToken(String token) {
        try {
           Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
           return JWT.require(algorithm)
                   .withIssuer(ISSUER)
                   .build()
                   .verify(token) // Verifica a validade do token
                   .getSubject(); // Obtém o assunto do token
        } catch (RuntimeException exception) {
            throw new RuntimeException();
        }
    }

    private Instant tokenCreationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant tokenExpirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).toInstant();
    }
}

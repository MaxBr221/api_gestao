package com.MaxBr221.GitHub.config.infra;

import com.MaxBr221.GitHub.model.Proprietario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.MaxBr221.GitHub.dtos.authDTO.DadosTokenJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public DadosTokenJWT createToken(Proprietario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var dadosToken = getExpiration();
            String token = JWT.create()
                    .withIssuer("api-gestao")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);
            Long segundosExpiracao = dadosToken.getEpochSecond();
            return new DadosTokenJWT(token, segundosExpiracao);

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-gestao")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant getExpiration(){
        return Instant.now().plus(10, ChronoUnit.MINUTES);
    }

}

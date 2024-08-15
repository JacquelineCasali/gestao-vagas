package com.gestao.domain.candidato.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gestao.domain.candidato.repository.CandidatoRepository;
import com.gestao.domain.candidato.DadosAutenticacao;
import com.gestao.domain.candidato.dto.AuthCandidatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidatoService {

    @Value("${security.token.secret.candidato}")
    private String secret;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidatoDTO execute(DadosAutenticacao dadosAutenticacao) throws AuthenticationException {
// verificando se o usuario existe
        var candidato = this.candidatoRepository.findByUsername(dadosAutenticacao.username()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Usuário/password incorrect");
                });
// verificar a senha são iguais
        //matches compara as senhas
        var passwordMatches = this.passwordEncoder.matches(dadosAutenticacao.password(), candidato.getPassword());
// se a senha for diferente
        if (!passwordMatches) {
            throw new AuthenticationException();

        }

var roles=Arrays.asList("CANDIDATO");

        Algorithm algorithm = Algorithm.HMAC256(secret);

      var expiresIn=Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create().withIssuer("gestao")
                //tempo  de duração do token
                .withExpiresAt(expiresIn)
                .withSubject(candidato.getId().toString())
                // restrições que o usuario tem

                .withClaim("roles",roles )
                .sign(algorithm);
        var authCandidato = AuthCandidatoDTO.builder()
                .access_token(token)
                .expirise_in(expiresIn.toEpochMilli())
                .roles(roles)
                .build();

        return authCandidato;
    }
}

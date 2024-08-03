package com.gestao.domain.candidato;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

    public AuthCandidatoDTO execute(DadosAutenticacao candidatoDTO) throws AuthenticationException {
// verificando se o usuario existe
        var candidato = this.candidatoRepository.findByName(candidatoDTO.name()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Name/password incorrect");
                });
// verificar a senha são iguais
        //matches compara as senhas
        var passwordMatches = this.passwordEncoder.matches(candidatoDTO.password(), candidato.getPassword());
// se a senha for diferente
        if (!passwordMatches) {
            throw new AuthenticationException();

        }
        Algorithm algorithm = Algorithm.HMAC256(secret);

      var expiresIn=Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create().withIssuer("gestao")
                //tempo  de duração do token
                .withExpiresAt(expiresIn)
                .withSubject(candidato.getId().toString())
                // restrições que o usuario tem

                .withClaim("roles", Arrays.asList("CANDIDATO"))
                .sign(algorithm);
        var authCandidato = AuthCandidatoDTO.builder()
                .access_token(token)
                .expirise_in(expiresIn.toEpochMilli())
                .build();

        return authCandidato;
    }
}

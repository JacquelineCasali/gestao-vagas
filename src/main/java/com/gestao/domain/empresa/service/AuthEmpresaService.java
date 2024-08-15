package com.gestao.domain.empresa.service;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.gestao.domain.candidato.dto.AuthCandidatoDTO;
import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.domain.empresa.dto.AuthEmpresaDTO;
import com.gestao.domain.empresa.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class AuthEmpresaService {

    @Value("${security.token.secret}")
    private String secret;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthEmpresaDTO execute(LoginDTO loginDTO) throws AuthenticationException {
// verificando se a empresa existe
    //orElseThrow lança exeção
    var empresa =this.empresaRepository.findByName(loginDTO.getName()).orElseThrow(
            ()->{
            throw new UsernameNotFoundException("Name/password incorrect");
            });
// verificar a senha são iguais
        //matches compara as senhas
var passwordMatches= this.passwordEncoder.matches(loginDTO.getPassword(), empresa.getPassword());
// se a senha for diferente
        if(!passwordMatches){
            throw new AuthenticationException();

        }
        //se for igual gera o token
            Algorithm algorithm= Algorithm.HMAC256(secret);
        var expiresIn=Instant.now().plus(Duration.ofHours(2));

            var token=    JWT.create().withIssuer("gestao")
            //tempo  de duração do token
                    .withExpiresAt(expiresIn)
                    .withClaim("roles", Arrays.asList("EMPRESA"))
                    .withSubject(empresa.getId().toString())
                    .sign(algorithm);


        var roles=Arrays.asList("EMPRESA");
        var authEmpresa = AuthEmpresaDTO.builder()
                .access_token(token)
                .expirise_in(expiresIn.toEpochMilli())
                .roles(roles)

                .build();


            return authEmpresa;









    }
}

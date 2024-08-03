package com.gestao.domain.candidato;


import com.gestao.infra.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Candidato execute(Candidato candidato){
        // verificando se o usuario existe
        this.candidatoRepository.findByNameOrEmail(candidato.getName(),candidato.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });

        var password=passwordEncoder.encode(candidato.getPassword());
        candidato.setPassword(password);

        return this.candidatoRepository.save(candidato);
        // return ResponseEntity.status(HttpStatus.CREATED).body(candidatoCreated);

    }


}

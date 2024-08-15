package com.gestao.domain.candidato.repository;


import com.gestao.domain.candidato.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatoRepository  extends JpaRepository<Candidato, Long> {


//    Optional para validação
    Optional<Candidato> findByUsernameOrEmail (String username, String email);
    Optional<Candidato> findByUsername(String username);

}

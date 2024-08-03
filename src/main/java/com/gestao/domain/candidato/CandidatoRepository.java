package com.gestao.domain.candidato;

import com.gestao.domain.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatoRepository  extends JpaRepository<Candidato, Long> {


//    Optional para validação
    Optional<Candidato> findByNameOrEmail (String name, String email);
    Optional<Candidato> findByName(String name);

}

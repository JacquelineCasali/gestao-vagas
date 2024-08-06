package com.gestao.domain.candidato;

import com.gestao.domain.candidato.entity.CandidatoVagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoVagaRepository extends JpaRepository<CandidatoVagaEntity, Long> {
}

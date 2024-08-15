package com.gestao.domain.candidato.repository;

import com.gestao.domain.candidato.entity.CandidatoVagaEntity;
import com.gestao.domain.vagas.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoVagaRepository extends JpaRepository<CandidatoVagaEntity, Long> {

    List<CandidatoVagaEntity> findByCandidatoId(Long id);
}

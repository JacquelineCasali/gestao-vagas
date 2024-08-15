package com.gestao.domain.vagas.service;

import com.gestao.domain.candidato.entity.CandidatoVagaEntity;
import com.gestao.domain.candidato.repository.CandidatoVagaRepository;
import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaTodasVagasByCandidatoService {


    @Autowired
    private CandidatoVagaRepository candidatoVagaRepository;

    public List<CandidatoVagaEntity> execute(Long candidatoId){

        return this.candidatoVagaRepository.findByCandidatoId(candidatoId);
    }
}

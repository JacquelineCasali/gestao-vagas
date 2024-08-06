package com.gestao.domain.candidato.service;

import com.gestao.domain.candidato.CandidatoRepository;
import com.gestao.domain.candidato.CandidatoVagaRepository;
import com.gestao.domain.candidato.entity.CandidatoVagaEntity;
import com.gestao.domain.vagas.VagaRepository;
import com.gestao.infra.exceptions.UserNotFoundException;
import com.gestao.infra.exceptions.VagaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//ligacação candidato vaga
@Service
public class IncricaoCandidatoVagaService {


@Autowired
private CandidatoRepository candidatoRepository;

@Autowired
private VagaRepository vagaRepository;

@Autowired
private CandidatoVagaRepository candidatoVagaRepository;

    public CandidatoVagaEntity execute(Long idCandidato, Long idVaga){
        // validar se o candidato existe
this.candidatoRepository.findById(idCandidato)
        // se o candidato nao encontrado
        .orElseThrow(()->{
            throw new UserNotFoundException();
        });

        //validar se a vaga existe
        this.vagaRepository.findById(idVaga)
                // se o candidato nao encontrado
                .orElseThrow(()->{
                    throw new VagaNotFoundException();
                });

        //candidata se iscrever na vaga
        var candidatoVaga= CandidatoVagaEntity.builder()
                .candidatoId(idCandidato)
                .vagaId(idVaga)
                .build();
        candidatoVaga =candidatoVagaRepository.save(candidatoVaga);
return candidatoVaga;
}



}

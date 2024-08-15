package com.gestao.domain.candidato.service;

import com.gestao.domain.candidato.repository.CandidatoRepository;
import com.gestao.domain.candidato.dto.PerfioCandidatoDTO;
import com.gestao.infra.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfioCandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    public PerfioCandidatoDTO execute(Long idCaditado){

        var candidato =this.candidatoRepository.findById(idCaditado).orElseThrow(
                ()->{
                    throw new UserNotFoundException();
                });

        var perfioCandidatoDTO= PerfioCandidatoDTO.builder()
                .description(candidato.getDescription())
                .name(candidato.getName())
                .username(candidato.getUsername())
                .email(candidato.getEmail())
                .id(candidato.getId())
                .build();

return perfioCandidatoDTO;


    }
}

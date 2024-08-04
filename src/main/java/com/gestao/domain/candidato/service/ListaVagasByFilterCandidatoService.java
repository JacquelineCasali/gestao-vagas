package com.gestao.domain.candidato.service;

import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaVagasByFilterCandidatoService {

    @Autowired
    private VagaRepository vagaRepository;

public List<Vaga> execute(String filter){
return this.vagaRepository.findByDescriptionContainingIgnoreCase(filter);
}


}

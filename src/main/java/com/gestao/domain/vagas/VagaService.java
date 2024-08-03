package com.gestao.domain.vagas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;


    public Vaga execute(Vaga vaga){

        return this.vagaRepository.save(vaga);

    }


}

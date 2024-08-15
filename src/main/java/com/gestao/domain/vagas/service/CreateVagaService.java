package com.gestao.domain.vagas.service;


import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import com.gestao.infra.exceptions.EmpresaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVagaService {
    @Autowired
    private VagaRepository vagaRepository;

@Autowired
private EmpresaRepository empresaRepository;
    public Vaga execute(Vaga vaga){
    // verificando se a empresa existe
        empresaRepository.findById(vaga.getEmpresaId()).orElseThrow(()->
        {throw new EmpresaNotFoundException();

        });


        return this.vagaRepository.save(vaga);
    }


}

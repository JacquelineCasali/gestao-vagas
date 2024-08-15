package com.gestao.domain.vagas.service;

import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaTodasVagasByEmpresaService {


    @Autowired
    private VagaRepository vagaRepository;

    public List<Vaga> execute(Long empresaId){
        return this.vagaRepository.findByEmpresaId(empresaId);
    }
}

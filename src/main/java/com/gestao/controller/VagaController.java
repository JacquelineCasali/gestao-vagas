package com.gestao.controller;


import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaService;
import com.gestao.domain.vagas.CreateVagaDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa/vagas")
public class VagaController {


    @Autowired
    private VagaService vagaService;



      //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    //autorização so para rota  empresa

    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateVagaDTO createVagaDTO, HttpServletRequest request) {
        try {
        // pegando o empresaId
            var idUser = request.getAttribute("empresa_id");
          //  vaga.setEmpresaId(Long.parseLong(idUser.toString()));
var vaga=Vaga.builder()
        .beneficio(createVagaDTO.getBeneficio())
        .empresaId(Long.parseLong(idUser.toString()))
        .description(createVagaDTO.getDescription())
        .nivelDaVaga(createVagaDTO.getNivelDaVaga())
        .build();
            ///var createVaga= this.vagaService.execute(vaga);
            return ResponseEntity.ok().body(this.vagaService.execute(vaga));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}

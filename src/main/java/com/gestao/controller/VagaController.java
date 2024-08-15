package com.gestao.controller;


import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import com.gestao.domain.vagas.service.CreateVagaService;
import com.gestao.domain.vagas.dto.CreateVagaDTO;
import com.gestao.domain.vagas.service.ListaTodasVagasByEmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/empresa/vagas")
@Tag(name = "Vagas",description = "Informação das Vagas")

public class VagaController {


    @Autowired
    private CreateVagaService vagaService;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private ListaTodasVagasByEmpresaService listaTodasVagasByEmpresaService;


    //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    //autorização so para rota  empresa
    @PreAuthorize("hasRole('EMPRESA')")

    @Operation(summary = "Cadastro de vagas por empresa", description = "Essa função é responsável por cadastrar as vagas dentro da empresa." +
            "  É necessário está logando em uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Vaga.class))
            }),

    })

// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")

    public ResponseEntity<Object> create(@Valid @RequestBody CreateVagaDTO createVagaDTO, HttpServletRequest request) {
        try {
            // pegando o empresaId
            var idEmpresa = request.getAttribute("empresa_id");
            //  vaga.setEmpresaId(Long.parseLong(idUser.toString()));
            var vaga = Vaga.builder()
                    .beneficio(createVagaDTO.getBeneficio())
                    .empresaId(Long.parseLong(idEmpresa.toString()))
                    .description(createVagaDTO.getDescription())
                    .nivelDaVaga(createVagaDTO.getNivelDaVaga())
                    .modalidadeVaga(createVagaDTO.getModalidadeVaga())
                    .requisitos(createVagaDTO.getRequisitos())
                    .build();
            ///var createVaga= this.vagaService.execute(vaga);
            return ResponseEntity.ok().body(this.vagaService.execute(vaga));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    // editar

    @PutMapping(value = "/{id}")
    // precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity editar(@Valid @PathVariable long id, @RequestBody CreateVagaDTO createVagaDTO, HttpServletRequest request) {


            Optional<Vaga> vaga = this.vagaRepository.findById(id);


            if (vaga.isPresent()) {

                Vaga updateVaga = vaga.get();


                updateVaga.setBeneficio(createVagaDTO.getBeneficio());
                updateVaga.setDescription(createVagaDTO.getDescription());
                updateVaga.setNivelDaVaga(createVagaDTO.getNivelDaVaga());
                updateVaga.setModalidadeVaga(createVagaDTO.getModalidadeVaga());
                updateVaga.setRequisitos(createVagaDTO.getRequisitos());
                this.vagaRepository.save(updateVaga);
                return ResponseEntity.ok(updateVaga);

            }

            return ResponseEntity.badRequest().body("Id  não encontrado");
        }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    //autorização so para rota  empresa
    @PreAuthorize("hasRole('EMPRESA')")

    @Operation(summary = "Listagem das Vagas", description = "Essa função é responsável por listar as vagas a empresa." +
            "  É necessário está logando em uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Vaga.class))
            }),

    })

// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")

    public ResponseEntity<Object>lisEmpresa( HttpServletRequest request){
var empresaId= request.getAttribute("empresa_id");
var result=this.listaTodasVagasByEmpresaService.execute(Long.parseLong(empresaId.toString()));
return ResponseEntity.ok().body(result);
        }

    }





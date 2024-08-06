package com.gestao.controller;


import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.empresa.Empresa;
import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import com.gestao.domain.vagas.VagaService;
import com.gestao.domain.vagas.CreateVagaDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/empresa/vagas")
@Tag(name = "Vagas",description = "Informação das Vagas")

public class VagaController {


    @Autowired
    private VagaService vagaService;

    @Autowired
    private VagaRepository vagaRepository;

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
            var idUser = request.getAttribute("empresa_id");
            //  vaga.setEmpresaId(Long.parseLong(idUser.toString()));
            var vaga = Vaga.builder()
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
                this.vagaRepository.save(updateVaga);
                return ResponseEntity.ok(updateVaga);

            }

            return ResponseEntity.badRequest().body("Id  não encontrado");
        }

    }
        // listar  as vagas
//@GetMapping
//    public List<Vaga> list(HttpServletRequest request){
//
//
//        return vagaRepository.findAll();
//}




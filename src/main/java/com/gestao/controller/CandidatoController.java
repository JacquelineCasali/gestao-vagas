package com.gestao.controller;


import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.candidato.CandidatoRepository;
import com.gestao.domain.candidato.dto.PerfioCandidatoDTO;
import com.gestao.domain.candidato.service.CandidatoService;
import com.gestao.domain.candidato.service.IncricaoCandidatoVagaService;
import com.gestao.domain.candidato.service.ListaVagasByFilterCandidatoService;
import com.gestao.domain.candidato.service.PerfioCandidatoService;
import com.gestao.domain.vagas.Vaga;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/candidato")
@Tag(name = "Candidato", description = "Informação do candidato")

public class CandidatoController {


    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private PerfioCandidatoService perfioCandidatoService;


    @Autowired
    IncricaoCandidatoVagaService incricaoCandidatoVagaService;

    @Autowired
    private ListaVagasByFilterCandidatoService listaVagasByFilterCandidatoService;


    //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Operation(summary = "Cadastro de candidato", description = "Essa função é responsável por cadastrar um candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Candidato.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody Candidato candidato) {
        try {
            var createCandidato = this.candidatoService.execute(candidato);
            return ResponseEntity.ok().body(createCandidato);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    // traz um candidato
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping

    @Operation(summary = "Perfil do Candidato", description = "Essa função é responsável por detalhar o candidato, precisa realizar o login do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PerfioCandidatoDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Candidato não encontrado")
    })


// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")
    //autorização
    //so candidato pode acessa essa rota
    @PreAuthorize("hasRole('CANDIDATO')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
// recuperando o usuario
        var idCandidato = request.getAttribute("candidato_id");
        try {
            var perfil = this.perfioCandidatoService.execute(Long.parseLong(idCandidato.toString()));
            return ResponseEntity.ok().body(perfil);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    //"Listagem de vagas disponivel para o candidato
    @GetMapping("/vagas")
    //so candidato pode acessa essa rota
    @PreAuthorize("hasRole('CANDIDATO')")

    //documentação swagger
    @Operation(summary = "Listagem de vagas disponivel para o candidato", description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro.   " +
            "Precisa realizar o login do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Vaga.class)))
            })
    })
// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")
    public List<Vaga> findVagaFilter(@RequestParam String filter) {
        return listaVagasByFilterCandidatoService.execute(filter);
    }


    //"Inscrição do candidato para uma vaga
    @PostMapping("/vagas/inscricao")
    //so candidato pode acessa essa rota
    @PreAuthorize("hasRole('CANDIDATO')")

    //documentação swagger
    @Operation(summary = "Inscrição do candidato para uma vaga", description = "Essa função é responsável por realizar a inscrição do candidato em uma vaga.   " +
            "Precisa realizar o login do candidato")

// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")


//inscrição candidato vaga
    public ResponseEntity<Object> inscricaoVaga(HttpServletRequest request, @RequestBody Long idVaga) {
        // id do candidato vem no login
        var idCandidato = request.getAttribute("candidato_id");
        try {
            var result = this.incricaoCandidatoVagaService.execute(Long.parseLong(idCandidato.toString()),idVaga);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
}
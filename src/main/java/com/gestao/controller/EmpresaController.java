package com.gestao.controller;


import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.empresa.Empresa;
import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.domain.empresa.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa",description = "Informação da empresa")

public class EmpresaController {


    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;



      //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Operation(summary = "Cadastro de empresa", description = "Essa função é responsável por cadastrar uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Empresa.class))
            }),
            @ApiResponse(responseCode = "400", description = "Empresa já existe")
    })



    public ResponseEntity<Object> create(@Valid @RequestBody Empresa empresa) {
        try {
            var createEmpresa = this.empresaService.execute(empresa);
            return ResponseEntity.ok().body(createEmpresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    // implementar metodos
    public  ResponseEntity excluir(@PathVariable Long id) {

        var foods = this.empresaRepository.findById(id).orElse(null);
        if (foods == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Id não encontrado");
        }
        // recupera o banco de dados
        empresaRepository.deleteById(id);
        return ResponseEntity.ok().body("Empresa excluida com sucesso");
    }


    }


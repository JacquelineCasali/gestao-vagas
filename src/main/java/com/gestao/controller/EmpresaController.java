package com.gestao.controller;


import com.gestao.domain.empresa.Empresa;
import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.domain.empresa.EmpresaService;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {


    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;



      //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Empresa empresa) {
        try {
            var createEmpresa = this.empresaService.execute(empresa);
            return ResponseEntity.ok().body(createEmpresa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @DeleteMapping("/{id}")
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


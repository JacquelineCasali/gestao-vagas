package com.gestao.controller;



import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.candidato.CandidatoRepository;
import com.gestao.domain.candidato.dto.PerfioCandidatoDTO;
import com.gestao.domain.candidato.service.CandidatoService;
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
@Tag(name = "Candidato",description = "Informação do candidato")

public class CandidatoController {


    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private PerfioCandidatoService perfioCandidatoService;
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Operation(summary = "Perfil do Candidato",description = "Essa função é responsável por detalhar o candidato, precisa realizar o login do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {
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






    @GetMapping("/vagas")
    //so candidato pode acessa essa rota
    @PreAuthorize("hasRole('CANDIDATO')")

    //documentação swagger
@Operation(summary = "Listagem de vagas disponivel para o candidato",description = "Essa função é responsável por listar todas as vagas disponíveis, baseada no filtro.   "  +
        "Precisa realizar o login do candidato")
  @ApiResponses({
       @ApiResponse(responseCode = "200",content = {
               @Content(array = @ArraySchema(schema = @Schema(implementation = Vaga.class)))
       })
  })
// precisa de autenticação
    @SecurityRequirement(name = "jwt_auth")
    public List<Vaga> findVagaFilter(@RequestParam String filter){
        return listaVagasByFilterCandidatoService.execute(filter);
}





//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('CANDIDATO')")
//    @Transactional
//    public ResponseEntity editar(@PathVariable long id, @RequestBody Candidato candidato) {
//
//
//
//        var users = this.candidatoRepository.findById(id).orElse(null);
//// verificando se o pedido existe
//        if (users == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Candidato não encontrado");
//        }
//
//        var password=passwordEncoder.encode(candidato.getPassword());
//        candidato.setPassword(password);
//        candidato.setId(id);
//        return ResponseEntity.ok(candidatoRepository.save(candidato));
//
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @DeleteMapping("/{id}")
//
//    //autorização
//    //so candidato pode acessa essa rota
//
//    public ResponseEntity delete(@PathVariable Long id) {
//
//        var candidato = this.candidatoRepository.findById(id).orElse(null);
//        if (candidato == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Id não encontrado");
//        }
//        // recupera o banco de dados
//        candidatoRepository.deleteById(id);
//        return ResponseEntity.ok().body("Candidato deletado com sucesso");
//    }
//

}

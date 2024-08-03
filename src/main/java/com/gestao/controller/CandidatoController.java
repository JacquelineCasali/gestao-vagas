package com.gestao.controller;



import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.candidato.CandidatoRepository;
import com.gestao.domain.candidato.CandidatoService;
import com.gestao.domain.candidato.PerfioCandidatoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/candidato")
public class CandidatoController {


    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private PerfioCandidatoService perfioCandidatoService;
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
   // traz um candidato
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
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




    //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Candidato candidato) {
        try {
            var createCandidato = this.candidatoService.execute(candidato);
            return ResponseEntity.ok().body(createCandidato);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity editar(@PathVariable long id, @RequestBody Candidato candidato) {
        var users = this.candidatoRepository.findById(id).orElse(null);
// verificando se o pedido existe
        if (users == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Candidato não encontrado");
        }

        var password=passwordEncoder.encode(candidato.getPassword());
        candidato.setPassword(password);
        candidato.setId(id);
        return ResponseEntity.ok(candidatoRepository.save(candidato));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")

    //autorização
    //so candidato pode acessa essa rota

    public ResponseEntity delete(@PathVariable Long id) {

        var candidato = this.candidatoRepository.findById(id).orElse(null);
        if (candidato == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Id não encontrado");
        }
        // recupera o banco de dados
        candidatoRepository.deleteById(id);
        return ResponseEntity.ok().body("Candidato deletado com sucesso");
    }


}

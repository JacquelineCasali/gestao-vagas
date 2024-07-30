package com.gestao.candidato;


import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidato")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @GetMapping
    public List<Candidato> listar() {
        return candidatoRepository.findAll();

    }

    // detalhar
//    ResponseEntity messnagem do codigo
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        // verificando se o id existe
        var Idcandidato = this.candidatoRepository.findById(id).orElse(null);
        if (Idcandidato == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Id não encontrado");
        }
        // recupera o banco de dados
        var detalhePedido = candidatoRepository.getReferenceById(id);
        return ResponseEntity.ok(detalhePedido);
    }

    //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Candidato candidato) {
       var passwordHashred = BCrypt.withDefaults().hashToString(12, candidato.getPassword().toCharArray());
        candidato.setPassword(passwordHashred);

        var candidatoCreated = this.candidatoRepository.save(candidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidatoCreated);

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

        var passwordHashred = BCrypt.withDefaults().hashToString(12, candidato.getPassword().toCharArray());
        candidato.setPassword(passwordHashred);
        candidato.setId(id);
        return ResponseEntity.ok(candidatoRepository.save(candidato));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        var foods = this.candidatoRepository.findById(id).orElse(null);
        if (foods == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Id não encontrado");
        }
        // recupera o banco de dados
        candidatoRepository.deleteById(id);
        return ResponseEntity.ok().body("Candidato deletado com sucesso");
    }


}

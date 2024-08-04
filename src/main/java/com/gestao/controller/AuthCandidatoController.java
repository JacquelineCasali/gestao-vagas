package com.gestao.controller;


import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.candidato.service.AuthCandidatoService;
import com.gestao.domain.candidato.DadosAutenticacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/candidato")
@Tag(name = "Login ",description = "Login do sistema")

public class AuthCandidatoController {

  @Autowired
  private AuthCandidatoService authCandidatoService;

  @PostMapping("/login")

  @Operation(summary = "Login do  candidato", description = "Essa função é responsável por logar o candidato no sistema")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
                  @Content(schema = @Schema(implementation = DadosAutenticacao.class))
          }),
          @ApiResponse(responseCode = "400", description = "Login invalido")
  })


  public ResponseEntity<Object> loginCandidato(@RequestBody DadosAutenticacao dadosAutenticacao) throws AuthenticationException {

    try {

      var loginCandidato= this.authCandidatoService.execute(dadosAutenticacao);
      return ResponseEntity.ok().body(loginCandidato);
    }catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }



  }
}

package com.gestao.controller;


import com.gestao.domain.candidato.AuthCandidatoService;
import com.gestao.domain.candidato.DadosAutenticacao;
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
public class AuthCandidatoController {

  @Autowired
  private AuthCandidatoService authCandidatoService;

  @PostMapping("/login")
  public ResponseEntity<Object> loginCandidato(@RequestBody DadosAutenticacao dadosAutenticacao) throws AuthenticationException {

    try {

      var loginCandidato= this.authCandidatoService.execute(dadosAutenticacao);
      return ResponseEntity.ok().body(loginCandidato);
    }catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }



  }
}

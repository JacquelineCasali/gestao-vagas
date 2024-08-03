package com.gestao.controller;



import com.gestao.domain.empresa.AuthEmpresaService;
import com.gestao.domain.empresa.dto.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/empresa")
public class AuthEmpresaController {

    @Autowired
  private AuthEmpresaService authEmpresaService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {

      try {

      var loginEmpresa= this.authEmpresaService.execute(loginDTO);
        return ResponseEntity.ok().body(loginEmpresa);
      }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      }



    }

}

package com.gestao.controller;




import com.gestao.domain.empresa.service.AuthEmpresaService;
import com.gestao.domain.empresa.dto.LoginDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Login ",description = "Login do sistema ")

public class AuthEmpresaController {

    @Autowired
  private AuthEmpresaService authEmpresaService;

    @PostMapping("/login")

    @Operation(summary = "Login da Empresa", description = "Essa função é responsável por logar a empresa no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = LoginDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Login invalido")
    })




    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {

      try {

      var loginEmpresa= this.authEmpresaService.execute(loginDTO);
        return ResponseEntity.ok().body(loginEmpresa);
      }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      }



    }

}

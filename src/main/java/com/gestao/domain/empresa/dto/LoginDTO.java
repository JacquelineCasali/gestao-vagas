package com.gestao.domain.empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// cria um construtor
public class LoginDTO {
    @Schema(example ="Teste", requiredMode = Schema.RequiredMode.REQUIRED)
    private  String name;
    @Schema(example ="1234" )
    private String password;

}

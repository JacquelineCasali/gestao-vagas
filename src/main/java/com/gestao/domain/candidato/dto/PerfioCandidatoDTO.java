package com.gestao.domain.candidato.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfioCandidatoDTO {

    // documentação
    @Schema(example ="Desenvolvedor Java" )
    private String  description;
     private  Long id;
    @Schema(example ="Pedro Costa" )
    private String name;
    @Schema(example ="pedro@terra.com.br" )
    private String email ;

    @Schema(example ="pedro" )
    private String username;
    }


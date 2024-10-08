package com.gestao.domain.vagas.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVagaDTO {

    // O QUE PASSA POR USUARIO
// para exemplo do swager

//    REQUIRED  campo obrigatorio

@Schema(example = "Vaga para desenvolvedor Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String  description;

    @Schema(example = "Junior")


    private  String nivelDaVaga;

    @Schema(example = "Plano de saúde")

    private String beneficio;
    @Schema(example = "Remoto")
    private String modalidadeVaga;
    @Schema(example = "Minino 2 Anos de React.js")
    private String requisitos;
}

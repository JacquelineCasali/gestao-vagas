package com.gestao.domain.vagas;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data


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


}

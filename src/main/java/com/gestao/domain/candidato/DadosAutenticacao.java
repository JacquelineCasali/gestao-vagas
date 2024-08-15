package com.gestao.domain.candidato;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosAutenticacao(


        @Schema(example ="pedro" )
        String username,
        @Schema(example ="1234" )
        String password) {
}

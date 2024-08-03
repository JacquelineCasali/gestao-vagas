package com.gestao.domain.empresa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthEmpresaDTO {

    private String access_token;

// quando que o token expira
    private Long expirise_in;
}

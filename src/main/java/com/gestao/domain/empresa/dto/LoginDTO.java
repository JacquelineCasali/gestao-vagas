package com.gestao.domain.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// cria um construtor
public class LoginDTO {
    private  String name;
    private String password;

}

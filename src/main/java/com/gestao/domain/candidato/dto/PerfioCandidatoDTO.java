package com.gestao.domain.candidato.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfioCandidatoDTO {

    private String  description;
     private  Long id;
    private String name;
    private String email ;


    }


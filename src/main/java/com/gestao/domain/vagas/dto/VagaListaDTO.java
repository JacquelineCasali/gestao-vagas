package com.gestao.domain.vagas.dto;



import com.gestao.domain.vagas.Vaga;



public record VagaListaDTO (

    // O QUE PASSA POR USUARIO
// para exemplo do swager

//    REQUIRED  campo obrigatorio
Long id,
     String  description,



      String nivelDaVaga,


     String beneficio,
     String modalidadeVaga,
     String requisitos,
    Long empresaId,
String empresa)
{public VagaListaDTO(Vaga vaga) {
    this(vaga.getId(),
            vaga.getDescription(),
            vaga.getNivelDaVaga(),
            vaga.getBeneficio(),
            vaga.getModalidadeVaga(),
            vaga.getRequisitos(),
            vaga.getEmpresaId(),
            vaga.getEmpresa().getName()
    );

                  }
              }

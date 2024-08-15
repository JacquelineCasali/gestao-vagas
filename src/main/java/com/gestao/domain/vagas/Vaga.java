package com.gestao.domain.vagas;


import com.gestao.domain.empresa.Empresa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@Entity(name="vagas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Esse Campo é obrigatório")
    @Schema(example = "Vaga para Desenvolvedor")
    private String  description;
    @NotBlank(message = "Esse Campo é obrigatório")
    @Schema(example = "Pleno")
    private  String nivelDaVaga;
    @Schema(example = "Plano de Saúde")
    private String beneficio;
    @NotBlank(message = "Esse Campo é obrigatório")
    @Schema(example = "Remoto")
    private String modalidadeVaga;
   @Schema(example = "Inglês Fluente")
    private String requisitos;



    // uma empresa atrelada a vaga

    //muintas vagas para uma empresa

    @ManyToOne
    //    @JoinColumn(name = "empresa_id") no da coluna no banco
    @JoinColumn(name = "empresa_id",insertable = false,updatable = false)
   private Empresa empresa;


 @Column(name ="empresa_id", nullable = false)
    private  Long empresaId;


    @CreationTimestamp
    private LocalDateTime createdAt;
}

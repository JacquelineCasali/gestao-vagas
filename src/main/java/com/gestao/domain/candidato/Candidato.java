package com.gestao.domain.candidato;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Data
@Entity(name="candidatos")

public class Candidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    @NotBlank(message = "Name não pode ser vazio")
    @Column(unique = true)
    @Schema(example ="Pedro Souza" )
    private String name;

    @NotNull
   @NotBlank(message = "Username não pode ser vazio")
    @Schema(example ="pedro" )
    private String username;

    @NotBlank(message = "Email não pode ser vazio")
    @Column(unique = true)
@Email(message = "O campo deve conter um email valido")
    @Schema(example ="pedro@terra.com.br" )
   private String email ;


@Length(min=4 , message = "A senha deve conter mais de 4 caracteres")

@Schema(example = "1234",minLength = 4,requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank(message = "Description não pode ser vazio")
    @Schema(example ="Desenvolvedor Java" )
  private String  description;
    private  String curriculum;

        @CreationTimestamp
    private LocalDateTime createdAt;
}

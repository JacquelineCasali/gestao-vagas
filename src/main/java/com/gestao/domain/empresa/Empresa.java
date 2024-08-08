package com.gestao.domain.empresa;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;


@Data
@Entity(name="empresas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    @NotBlank(message = "Name não pode ser vazio")
    @Column(unique = true)
    @Schema(example = "Teste")
    private String name;


    @NotBlank(message = "Email não pode ser vazio")
    @Column(unique = true)
    @Email(message = "O campo deve conter um email valido")
    @Schema(example = "teste@gmail.com")
    private String email ;


    @Length(min=4 , message = "A senha deve conter mais de 4 caracteres")
   @Schema(example = "1234")
    private String password;
    @Schema(example = "Empresa de desenvolvimento de sistemas")
    private String  description;

    @URL(message = "O campo deve conter uma url valida")
    @Schema(example = "https://www.globo.com/")
    private  String webSite;




    @CreationTimestamp
    private LocalDateTime createdAt;
}

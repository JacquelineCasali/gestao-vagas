package com.gestao.infra.sprigdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration classe de configuração da documentacao
@Configuration
public class SpringdocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                //@OpenAPIDefinition(
                .info(new Info().title("Gestão")
                        .description("Api responsável por gestão")
                        .version("1")
                )


                .components(new Components()
                        .addSecuritySchemes("jwt_auth",
                                new SecurityScheme()
                                        .name("jwt_auth")
                                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")

                        ));
    }

}

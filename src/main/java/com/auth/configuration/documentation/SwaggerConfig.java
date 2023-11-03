package com.auth.configuration.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi detailsApi() {
        return GroupedOpenApi.builder()
                .group("springboot-authentication")
                .packagesToScan("com.auth.controllers")
                .build();
    }

    @Bean
    public OpenAPI informationApi() {
        return new OpenAPI()
                .info(new Info().title("SpringBoot Security Authentication API")
                        .description("Exemplo de uma API de cadastro de produtos com usuário autenticado")
                        .version("v1.0")
                        .license(new License().name("Licença: israelemf").url("https://github.com/israelemf")));
    }
}

package com.one.foroapi.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Foro API")
                                .version("1.0")
                                .description("Foro API with Spring Boot 3 - RESTful service using springdoc-openapi")
                );
    }
}

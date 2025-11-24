package com.cortagasto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI para documentação da API.
 */
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI cortaGastoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CortaGasto API")
                        .description("API RESTful para controle de gastos pessoais. " +
                                   "Sistema simples e eficiente para registrar, visualizar e gerenciar despesas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("CortaGasto Team")
                                .email("contato@cortagasto.com")));
    }
}


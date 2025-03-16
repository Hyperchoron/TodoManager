package com.mblosev.todomanager;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class OpenApiConfig {
    private Environment environment;

    @Bean
    OpenAPI defineOpenApi() {
        Server server = new Server();

        server.setUrl(environment.getProperty("api.server.url"));
        server.setDescription("Todo list manager");

        Contact contact = new Contact();

        contact.setName("Матвей Лосев");
        contact.setEmail("nothing@nothing.com");

        Info info = new Info()
        .title("REST API для управления задачами")
        .version("1.0")
        .description("Здесь можно создавать задачи.")
        .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}

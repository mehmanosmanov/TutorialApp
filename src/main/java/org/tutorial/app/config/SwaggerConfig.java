package org.tutorial.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@Configuration
public class SwaggerConfig {
    private static final String TITLE = "Tutorial Microservice REST API";
    private static final String VERSION = "1.0";
    private static final String AUTHORIZATION = "Authorization";
    private static final String HEADER = "header";
    private static final String API_KEY = "apiKey";
    private static final String GLOBAL = "global";
    private static final String ACCESS_EVERYTHING = "accessEverything";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();

    }
}


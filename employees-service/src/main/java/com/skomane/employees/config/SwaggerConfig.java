package com.skomane.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket SwaggerApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getAppInfo());
    }

    private ApiInfo getAppInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Employee Service")
                .version("1.0")
                .description("REST API for Spring Boot Employee Service Application")
                .contact(new Contact("Mogau", "http://skomane.com", "mogau@gmail.com"))
                .license("Apache Licence Version 2.0")
                .build();
    }
}

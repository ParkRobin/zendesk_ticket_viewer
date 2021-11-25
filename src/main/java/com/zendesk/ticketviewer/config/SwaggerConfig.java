package com.zendesk.ticketviewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket creditMonitorApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Zendesk ticket viewer project API")
                .description("Zendesk ticket viewer project")
                .termsOfServiceUrl("http://www.zendesk.com")
                .contact(new Contact("Robin Park", "https://github.com/ParkRobin",
                        "lpiao@ncsu.edu"))
                .version("1.0")
                .build();
    }
}

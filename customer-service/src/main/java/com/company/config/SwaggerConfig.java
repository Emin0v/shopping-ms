package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company"))
                .build();
    }

//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.company"))
//                .paths(PathSelectors.regex("/.*"))
//                .build().apiInfo(apiEndPointInfo());
//    }
//
//    private ApiInfo apiEndPointInfo() {
//        return new ApiInfoBuilder().title("Spring Boot Swagger Example")
//                .description("User API Documentation")
//                .contact(new Contact("com.company","",""))
//                .license("Apache 2.0")
//                .build();
//
//    }
}
//package it.academy.softwarerestoMenu.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket api() { // http://localhost:8080/swagger-ui.html
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("it.academy.softwarerestoMenu.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}
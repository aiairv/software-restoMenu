//package it.academy.softwarerestoMenu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//package it.academy.softwarerestoMenu.config;//package it.academy.softwarerestoMenu.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
//@Configuration
//@EnableWebMvc
//@EnableSwagger2WebMvc
//public class SwaggerConfig {
//    @Bean
//    public Docket api() { // http://localhost:9090/swagger-ui/index.html
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("it.academy.softwarerestoMenu"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}
//@Configuration
//@EnableWebMvc
//@EnableSwagger2WebMvc
//public class SwaggerConfig {
//    @Bean
//    public Docket api() { // http://localhost:9090/swagger-ui/index.html
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("it.academy.softwarerestoMenu.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}

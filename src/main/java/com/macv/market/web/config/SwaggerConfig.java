package com.macv.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Indicar a Spring que será un archivo de configuración

//Habilitar en la clase el uso de Swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //Se indica a Spring que será un Bean
    //Docket un tipo de objeto de SpringFox
    @Bean
    public Docket api(){
        //DocumentationType: indica el tipo de documentación que se usará
        //.select().apis se indica qué queremos que exponga en la documentación
        //apis() recibe un parámetro que permite indicar sobre qué paquete se expondrá la documentación
        //.build() construye la documentación y la lanza en un endpoint
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.macv.market.web.controller"))
                .build();
    }
}

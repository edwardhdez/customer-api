package com.consultec.customer.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

/**
 * Configuración para presentar los endpoints en Swagger
 *
 * @author Edward Hernandez
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String EMPRESA = "CONSULTEC";
    private static final String EMAIL = "SOPORTE@CONSULTEC.COM.DO";
    private static final String TITULO_API = "Customer API";
    private static final String DESCRIPCION_API = "CUSTOMER CRUD";
    private static final String VERSION_API = "1.0";

    private static final Set<String> PRODUCES_AND_CONSUMES
            = new HashSet<>(Arrays.asList("application/json",
                    "application/xml"));

    /**
     * Información básica del servicio
     *
     * @return (ApiInfo) información del API
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                TITULO_API,
                DESCRIPCION_API,
                VERSION_API,
                "",
                new Contact(EMPRESA, "", EMAIL),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }

    /**
     * Crea la interfaz entre Swagger y Spring
     *
     * @return Interfaz de Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .produces(PRODUCES_AND_CONSUMES)
                .consumes(PRODUCES_AND_CONSUMES);

    }

}

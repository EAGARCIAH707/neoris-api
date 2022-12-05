package com.neoris.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =
        @Info(
                title = "neoris-api",
                version = "v0",
                description = "Application Programming Interface",
                contact =
                @Contact(
                        name = "Edisson Andres Garcia",
                        email = "eagarciah707@gmail.com",
                        url = "https://www.linkedin.com/in/edisson-andres-garcia-herrera-63a91517b"),
                license =
                @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
        servers = {
                @Server(url = "http://localhost:8080/neoris-api/"),
                @Server(
                        url = "https://neoris-api-3txis2veua-uc.a.run.app/neoris-api/")
        })
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenApiConfig {
}

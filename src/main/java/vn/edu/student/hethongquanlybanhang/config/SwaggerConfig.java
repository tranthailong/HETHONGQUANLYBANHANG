package vn.edu.student.hethongquanlybanhang.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("Hệ Thống Quản Lý Bán Hàng API")
                        .version("1.0")
                        .description("REST API Spring Boot"))

                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Project"));

    }

}
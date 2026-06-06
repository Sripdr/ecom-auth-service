package in.ecom.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("ECOM-USER-JWT-AUTHENTICATION-SERVICE")
                        .description("This Is User Authentication Service With JWT Token")
                        .version("1.0.0")
        );
    }

}

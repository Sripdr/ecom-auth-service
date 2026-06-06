package in.ecom.config;

import in.ecom.client.UserInfoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(basePackages = "in.ecom.client",types = {UserInfoClient.class})
@Configuration
public class UserInfoClientConfig {
}
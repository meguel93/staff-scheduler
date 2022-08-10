package za.co.staffschedule.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class OpenApiSwaggerConfig {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
    @Value("${application.description}")
    private String description;

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Bean
    public OpenAPI currentVersionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(name)
                        .version(version)
                        .description(description)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

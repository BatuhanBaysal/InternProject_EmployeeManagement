package com.tutorial.employeemanagementbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // is a Spring annotation that specifies that a class is a configuration class in a Spring application and allows Bean definitions to be made in this class. This class is used as a structure managed by the Spring container.
public class WebConfig implements WebMvcConfigurer {
    // URL: http://localhost:8080/swagger-ui/index.html
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
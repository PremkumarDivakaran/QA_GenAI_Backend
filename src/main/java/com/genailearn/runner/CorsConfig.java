package com.genailearn.runner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all endpoints
                        .allowedOrigins("*")  // Allow all origins (change to "http://localhost:3000" later)
                        .allowedMethods("*")  // Allow all HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(false);  // Disable credentials for testing
            }
        };
    }
}

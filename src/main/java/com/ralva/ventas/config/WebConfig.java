package com.ralva.ventas.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer{
   /*  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "Authorization", "X-Requested-With") // Especificar encabezados permitidos
                        .exposedHeaders("Content-Disposition") // Especificar encabezados expuestos
                        .allowCredentials(true);
            }
        };
    } */

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/v3/api-docs")
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With")
                .exposedHeaders("Content-Disposition")
                .allowCredentials(true);
        
        registry.addMapping("/swagger-ui/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With")
                .exposedHeaders("Content-Disposition")
                .allowCredentials(true);
    }
}

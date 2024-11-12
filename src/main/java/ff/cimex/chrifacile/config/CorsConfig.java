package ff.cimex.chrifacile.config;

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
                registry.addMapping("/**") // Permet toutes les routes
                        .allowedOrigins("*") // Permet toutes les origines
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permet ces méthodes HTTP
                        .allowedHeaders("*") // Permet tous les headers
                        .allowCredentials(false); // Désactive l'utilisation des cookies
            }
        };
    }
}
package nettee.config;

import nettee.properties.CorsProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCorsConfig implements WebMvcConfigurer {
    
    private final CorsProperties corsProperties;
    
    public WebMvcCorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }
    
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        for(var endpoint: corsProperties.endpoints()){
            var path = endpoint.path();
            var allowed = endpoint.allowed();
            var exposed = endpoint.exposed();
            var maxAge = endpoint.maxAge();
            
            registry.addMapping(path)
                    .allowedHeaders(allowed.headers())
                    .allowedMethods(allowed.methods())
                    .allowedOrigins(allowed.origins())
                    .allowCredentials(allowed.credentials())
                    .allowPrivateNetwork(allowed.privateNetwork())
                    .exposedHeaders(exposed.headers())
                    .maxAge(maxAge);
        }
    }
}

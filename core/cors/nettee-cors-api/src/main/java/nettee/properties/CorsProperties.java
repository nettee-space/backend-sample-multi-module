package nettee.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.cors")
public record CorsProperties (
        MappedCorsProperties[] endpoints
) {
}
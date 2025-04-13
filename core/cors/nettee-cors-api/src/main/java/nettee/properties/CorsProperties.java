package nettee.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.cors")
public record CorsProperties (
        MappedCorsProperties[] endpoints
) {
    public CorsProperties {
        for (var endpoint : endpoints) {
            System.out.println(endpoint);
        }
    }
}
package nettee.properties;

import nettee.properties.allowed.CorsAllowedProperties;
import nettee.properties.exposed.CorsExposedProperties;

public record MappedCorsProperties(
        String path,
        CorsAllowedProperties allowed,
        CorsExposedProperties exposed,
        Long maxAge
) {
    public MappedCorsProperties {
        if (maxAge == null) {
            maxAge = 3600L;
        }
    }
}

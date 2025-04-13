package properties.exposed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record CorsExposedProperties(
        String[] headers
) {
}

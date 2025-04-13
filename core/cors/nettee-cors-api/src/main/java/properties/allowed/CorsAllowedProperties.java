package properties.allowed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record CorsAllowedProperties(
        String[] headers,
        String[] methods,
        String[] origins,
        Boolean credentials,
        Boolean privateNetwork
) {
    public CorsAllowedProperties {
        if(credentials == null){
            credentials = true;
        }
    }
}

package netttee.client.propeties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("app.client")
public record ClientProperties(
        String baseUrl,
        Map<String, String> url
) {
    public ClientProperties {
        System.out.println("default url: " + baseUrl);
        
        for(Map.Entry<String, String> entry : url.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

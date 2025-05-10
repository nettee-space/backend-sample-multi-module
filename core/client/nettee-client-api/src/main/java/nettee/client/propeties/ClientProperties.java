package nettee.client.propeties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@ConfigurationProperties("app.client")
public record ClientProperties(
        String baseUrl,
        Map<String, String> url
) {
    public ClientProperties {
        log.debug("baseUrl url: {}", baseUrl);
        
        for(Entry<String, String> entry : url.entrySet()) {
            log.debug((entry.getKey() + ": " + entry.getValue()));
        }
    }
}

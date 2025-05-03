package nettee.config;

import netttee.propeties.ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
public class RestClientConfig {
    
    @Bean
    public RestClient restClient(ClientProperties clientProperties) {
        return RestClient.builder()
                .baseUrl(clientProperties.baseUrl())
                .build();
    }
}

package nettee.restclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nettee.restclient.NetteeClient;
import nettee.client.propeties.ClientProperties;
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
    
    @Bean
    public NetteeClient customClient(RestClient restClient,
                                     ClientProperties clientProperties,
                                     ObjectMapper objectMapper) {
        return new NetteeClient(restClient, clientProperties, objectMapper);
    }
}

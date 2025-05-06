package nettee.restclient.config;

import nettee.restclient.NetteeClient;
import netttee.client.propeties.ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ClientProperties.class)
public class RestClientConfig {
    
    @Bean
    public RestClient restClient(ClientProperties clientProperties) {
        var restClient =  RestClient.builder()
                .baseUrl(clientProperties.baseUrl())
                .build();
        
        // RestClient 등록 시, NetteeClient 수동 세팅
        NetteeClient.init(restClient, clientProperties);
        
        return restClient;
    }
}

package nettee.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "nettee")
public class RedisApiTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApiTestApplication.class, args);
    }
}

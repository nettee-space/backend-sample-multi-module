package nettee.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "me.nettee") // 엔티티 패키지 지정
@EnableJpaRepositories(basePackages = "me.nettee") // 리포지토리 패키지 지정
public class JpaConfig {

}

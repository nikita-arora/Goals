package in.goals;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
@Configuration
@EnableAsync
@EnableJpaRepositories(basePackages = { "in.goals.dbaccess", "in.goals" })
public class Config {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

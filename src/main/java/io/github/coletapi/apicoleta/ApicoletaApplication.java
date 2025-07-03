package io.github.coletapi.apicoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
public class ApicoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicoletaApplication.class, args);
	}

}

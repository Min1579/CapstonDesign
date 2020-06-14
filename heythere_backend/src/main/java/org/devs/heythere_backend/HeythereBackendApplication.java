package org.devs.heythere_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HeythereBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(HeythereBackendApplication.class, args);

	}
}

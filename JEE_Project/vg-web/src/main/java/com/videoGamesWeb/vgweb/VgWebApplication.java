package com.videoGamesWeb.vgweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.videoGamesWeb"})
@EntityScan(basePackages = {"com.videoGamesWeb.vgcore"})
@EnableJpaRepositories(basePackages = {"com.videoGamesWeb.vgcore"})
public class VgWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(VgWebApplication.class, args);
	}

}

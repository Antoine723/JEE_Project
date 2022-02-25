package com.videoGamesWeb.vgweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication(scanBasePackages = {"com.videoGamesWeb"})
@EntityScan(basePackages = {"com.videoGamesWeb.vgcore"})
@EnableJpaRepositories(basePackages = {"com.videoGamesWeb.vgcore"})
public class VgWebApplication {
	public final static String SESSION_USER_ID = "userID";
	public final static String SESSION_BASKET = "basket";
	public final static String ERROR_MSG = "error_msg";

	public final static BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder(10, new SecureRandom());

	public static void main(String[] args) {
		SpringApplication.run(VgWebApplication.class, args);
	}

}

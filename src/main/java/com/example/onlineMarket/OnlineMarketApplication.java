package com.example.onlineMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.onlineMarket.repository")
public class OnlineMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMarketApplication.class, args);
	}

}
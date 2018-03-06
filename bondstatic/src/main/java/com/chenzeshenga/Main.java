package com.chenzeshenga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;

@SpringBootApplication
@ComponentScan(basePackages = "com.chenzeshenga")
@EnableScheduling
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Jedis jedis() {
		Jedis jedis = new Jedis("47.93.248.132");
		jedis.auth("Charlotte34");
		return jedis;
	}

}

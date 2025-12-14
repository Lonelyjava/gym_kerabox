package com.gym.kerabox;

/*Developer : Kundan Kumar
 * date : 14-12-2025
 * Project - Gym Management
 * 
 * 
 * */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GymKeraboxApplication {

	public static void main(String[] args) {

		SpringApplication.run(GymKeraboxApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

package com.gym.kerabox;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
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

import com.gym.kerabox.auth.entity.Role;
import com.gym.kerabox.auth.entity.User;
import com.gym.kerabox.auth.reposistory.UserRepository;
import com.gym.kerabox.auth.service.AuthenticationService;

@SpringBootApplication
public class GymKeraboxApplication {

	public static void main(String[] args) {

		SpringApplication.run(GymKeraboxApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner initData(UserRepository userRepository, AuthenticationService authSer) {
		return args -> {

//		for super admin creation----

			Optional<User> byEmail = userRepository.findByEmail("superadmin@gmail.com");
			if (!byEmail.isPresent()) {
				User user = new User();
				user.setFirstName("super");
				user.setLastName("admin");
				user.setEmail("superadmin@gmail.com");
				String password = "admin";
				String encrtpassword = authSer.passwordEncode(password);
				user.setPassword(encrtpassword);
				user.setRole(Role.SUPERADMIN);
				userRepository.save(user);
				System.out.println("super admin created ");
			} else {
				System.out.println("super admin already existed");
			}

		};
	}
}
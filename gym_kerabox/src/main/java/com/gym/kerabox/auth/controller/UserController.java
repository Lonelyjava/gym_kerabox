package com.gym.kerabox.auth.controller;


/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")

public class UserController {

	@PostMapping("/testUser")
	public ResponseEntity<String> seyHello() {
		return ResponseEntity.ok("Hello from User Controller!");
	}
}
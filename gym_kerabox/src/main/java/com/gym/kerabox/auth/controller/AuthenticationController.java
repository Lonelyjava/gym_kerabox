package com.gym.kerabox.auth.controller;

/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.auth.dto.AuthenticationRequest;
import com.gym.kerabox.auth.dto.AuthenticationResponse;
import com.gym.kerabox.auth.dto.RegisterRequest;
import com.gym.kerabox.auth.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/auth-service")
@Tag(name = "Auth Controller", description = "User management APIs")
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	@PostMapping("/register")
	@Operation(summary = "register new user")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		try {
			String response = authenticationService.register(registerRequest);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthenticationResponse> refresh(@RequestParam("token") String refreshToken) {
		try {
			AuthenticationResponse response = authenticationService.refreshToken(refreshToken);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/validateToken")
	public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
		try {
			Boolean response = authenticationService.validateToken(token);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

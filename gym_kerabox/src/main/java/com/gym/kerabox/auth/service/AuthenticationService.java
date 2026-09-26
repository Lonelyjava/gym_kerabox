package com.gym.kerabox.auth.service;

/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.kerabox.auth.dto.AuthenticationRequest;
import com.gym.kerabox.auth.dto.AuthenticationResponse;
import com.gym.kerabox.auth.dto.RegisterRequest;
import com.gym.kerabox.auth.entity.Role;
import com.gym.kerabox.auth.entity.User;
import com.gym.kerabox.auth.reposistory.UserRepository;

@Service
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public String register(RegisterRequest registerRequest) {
		Optional<User> byEmail = userRepository.findByEmail(registerRequest.getEmail());
		if (!byEmail.isPresent()) {
			User user = new User();
			user.setFirstName(registerRequest.getFirstName());
			user.setLastName(registerRequest.getLastName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole(Role.USER);
			userRepository.save(user);
			String jwtToken = jwtService.generateToken(user);
			String refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
//			AuthenticationResponse response = new AuthenticationResponse();
//			response.setAuthenticationToken(jwtToken);
//			response.setRefreshToken(refreshToken);
			String response = "Register Successfully";
			return response;
		} else {
			String response = "Email already existed";
			return response;
		}

	}

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
				authenticationRequest.getPassword()));
		User user = userRepository.findByEmail(authenticationRequest.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
		AuthenticationResponse response = new AuthenticationResponse();
		response.setAuthenticationToken(jwtToken);
		response.setRefreshToken(refreshToken);
		return response;
	}

	public AuthenticationResponse refreshToken(String refreshToken) {
		String email = jwtService.getEmailFromToken(refreshToken);
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
		String jwtToken = jwtService.generateToken(user);
		String newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
		AuthenticationResponse response = new AuthenticationResponse();
		response.setAuthenticationToken(jwtToken);
		response.setRefreshToken(newRefreshToken);
		return response;
	}

	public Boolean validateToken(String token) {
		return jwtService.validateToken(token);
	}

	public String passwordEncode(String password) {
		// TODO Auto-generated method stub
		return passwordEncoder.encode(password);
	}
}
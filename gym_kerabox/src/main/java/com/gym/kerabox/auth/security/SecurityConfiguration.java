package com.gym.kerabox.auth.security;

/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gym.kerabox.auth.entity.Role;
import com.gym.kerabox.auth.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	// Constructor injection - no Lombok required
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF for REST API
				.csrf(csrf -> csrf.disable())
				// Authorization rules
				.authorizeHttpRequests(request -> request
						// Authentication APIs
						.requestMatchers("/api/v1/auth-service/**", "/v3/api-docs/**", "/swagger-ui/**",
								"/swagger-ui.html", "/webjars/**")
						.permitAll()
						// Admin APIs
						.requestMatchers("/api/v1/admin/**").hasAuthority(Role.ADMIN.name())
						// User APIs
						.requestMatchers("/api/v1/user/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
						// Everything else requires authentication
						.anyRequest().authenticated())
				// JWT authentication is stateless
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Authentication provider
				.authenticationProvider(authenticationProvider)
				// JWT filter before UsernamePasswordAuthenticationFilter
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
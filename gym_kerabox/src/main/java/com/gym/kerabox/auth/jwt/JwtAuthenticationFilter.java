package com.gym.kerabox.auth.jwt;

/**
 * 
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gym.kerabox.auth.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");

		// Check Authorization header
		if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Remove "Bearer " from token
		final String jwt = authHeader.substring(7);
		try {
			// Extract username/email from JWT
			final String userEmail = jwtService.extractUserName(jwt);
			// Check user and existing authentication
			if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
				// Validate JWT
				if (jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					// Set request details
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// Set authentication in SecurityContext
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

		} catch (Exception e) {
			// Invalid or expired JWT
			SecurityContextHolder.clearContext();
		}
		// Continue filter chain
		filterChain.doFilter(request, response);
	}
}
package com.gym.kerabox.exceptionhandler;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ErrorResponse {

	private int statusCode;
	private String message;

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}
	
	public ErrorResponse(int statusCode,String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

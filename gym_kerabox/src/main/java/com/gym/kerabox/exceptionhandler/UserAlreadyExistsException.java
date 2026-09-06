package com.gym.kerabox.exceptionhandler;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
public class UserAlreadyExistsException extends RuntimeException{

	private String message;
	
	public UserAlreadyExistsException() {
		
	}

	public UserAlreadyExistsException(String message) {

		super(message);
		this.message = message;
	}

}

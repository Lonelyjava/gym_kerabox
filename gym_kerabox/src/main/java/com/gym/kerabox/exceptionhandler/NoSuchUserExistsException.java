package com.gym.kerabox.exceptionhandler;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
public class NoSuchUserExistsException extends RuntimeException {

	private String message;

	public NoSuchUserExistsException() {
	}

	public NoSuchUserExistsException(String msg) {
		super(msg);
		this.message = msg;
	}
	

}

package com.gym.kerabox.validator;

/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import com.gym.kerabox.dto.UserDto;

public class UserValidator {

	public static String saveUserValidation(UserDto userDto) {

		StringBuilder message = new StringBuilder();

		if (userDto.getFirstname() == null || userDto.getFirstname().trim().isEmpty()) {
			message.append("Firstname is required. ");
		}

		if (userDto.getLastname() == null || userDto.getLastname().trim().isEmpty()) {
			message.append("Lastname is required. ");
		}
		if (userDto.getMobile() == null || userDto.getMobile().trim().isEmpty()) {
			message.append("Mobile is required. ");
		}
		if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
			message.append("Email is required. ");
		}
		if (userDto.getAddress1() == null || userDto.getAddress1().trim().isEmpty()) {
			message.append("Address1 is required. ");
		}
		if (userDto.getAddress2() == null || userDto.getAddress2().trim().isEmpty()) {
			message.append("Address2 is required. ");
		}
		if (userDto.getDistric() == null || userDto.getDistric().trim().isEmpty()) {
			message.append("District is required. ");
		}
		if (userDto.getState() == null || userDto.getState().trim().isEmpty()) {
			message.append("State is required. ");
		}
		if (userDto.getPincode() == 0) {
			message.append("Pincode is required. ");
		}
		if (userDto.getGender() == null || userDto.getGender().trim().isEmpty()) {
			message.append("Gender is required. ");
		}
		if (userDto.getStatus() == null || userDto.getStatus().trim().isEmpty()) {
			message.append("Status is required. ");
		}

		return message.length() > 0 ? message.toString().trim() : null;
	}

}

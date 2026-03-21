package com.gym.kerabox.validator;

import com.gym.kerabox.dto.UserDto;

public class UserValidator {
	
	
	public static UserDto saveUserValidation(UserDto userDto) {
		
		if(userDto.getFirstname() == null) {
			
		}
		
		return userDto;
	}

}

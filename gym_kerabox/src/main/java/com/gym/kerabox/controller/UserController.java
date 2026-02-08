package com.gym.kerabox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.UserService;

@RestController
@RequestMapping(UserConstant.GYM_KERABOX)
//@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(UserConstant.SAVE_USER)
	public ResponseEntity<?> saveUserDetails(@RequestBody UserDto userDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			UserEntity saveUser = userService.saveUser(userDto);
			apiResponse.setResponseCode(200);
			apiResponse.setMessage("Data saved successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(saveUser);
		} catch (Exception e) {
//			log.info("Service method called using @Slf4j"); kundan kumar
			e.printStackTrace();
		}

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/saveEmp")
	public ResponseEntity<?> saveEmp() {
		userService.saveEmp();
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUser() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<UserEntity> getUser = userService.getUser();
			apiResponse.setResponseCode(200);
			apiResponse.setMessage("Get All User successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(getUser);
		} catch (Exception e) {
//			log.info("Service method called using @Slf4j"); kundan kumar
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}

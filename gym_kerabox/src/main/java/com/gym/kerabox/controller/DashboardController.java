package com.gym.kerabox.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.DashboardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(UserConstant.GYM_KERABOX)
public class DashboardController {

	private static final Logger logger = Logger.getLogger(DashboardController.class.getName());

	@Autowired
	DashboardService dashboardService;

	@GetMapping(UserConstant.ACTIVE_USER)
	@Operation(summary = "get active user details")
	public ResponseEntity<?> getActiveUser(@RequestParam("status") String status) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<UserEntity> getUser = dashboardService.getActiveUser(status);
			apiResponse.setResponseCode(200);
			apiResponse.setCount(getUser.size());
			apiResponse.setMessage("get active User successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(getUser);
		} catch (Exception e) {
			logger.info("Service method called using @ACTIVE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.INACTIVE_USER)
	@Operation(summary = "get inactive user details")
	public ResponseEntity<?> getInactiveUser(@RequestParam("status") String status) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<UserEntity> getUser = dashboardService.getActiveUser(status);
			apiResponse.setResponseCode(200);
			apiResponse.setCount(getUser.size());
			apiResponse.setMessage("get inactive User successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(getUser);
		} catch (Exception e) {
			logger.info("Service method called using @INACTIVE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.TOTAL_USER)
	@Operation(summary = "get total user details")
	public ResponseEntity<?> getTotalUser() {
		ApiResponse apiResponse = new ApiResponse();
		try {
//			UserDto getUser = userService.updateUser(userDto);
			apiResponse.setResponseCode(200);
//			apiResponse.setCount(getUser.getId());
			apiResponse.setMessage("get total User successfully.");
			apiResponse.setErrorMessage(false);
//			apiResponse.setData(getUser);
		} catch (Exception e) {
			logger.info("Service method called using @TOTAL_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.NEW_USER)
	@Operation(summary = "get new user details")
	public ResponseEntity<?> getNewUser() {
		ApiResponse apiResponse = new ApiResponse();
		try {
//			UserDto getUser = userService.updateUser(userDto);
			apiResponse.setResponseCode(200);
//			apiResponse.setCount(getUser.getId());
			apiResponse.setMessage("get new User successfully.");
			apiResponse.setErrorMessage(false);
//			apiResponse.setData(getUser);
		} catch (Exception e) {
			logger.info("Service method called using @NEW_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}

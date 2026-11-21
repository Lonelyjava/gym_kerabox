package com.gym.kerabox.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.entity.MembersEntity;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.DashboardService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(UserConstant.GYM_KERABOX)
public class DashboardController {

	private static final Logger logger = Logger.getLogger(DashboardController.class.getName());

	@Autowired
	DashboardService dashboardService;

	@GetMapping(UserConstant.ACTIVE_MEMBERS)
	@Operation(summary = "get active members details")
	public ResponseEntity<?> getActiveMembers(@RequestParam("status") String status) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<MembersEntity> getActiveMembers = dashboardService.getActiveMembers(status);
			if(getActiveMembers.size()>0) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getActiveMembers.size());
				apiResponse.setMessage("get active User successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getActiveMembers);
			}else {
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("Not found any :"+status+" -User");
			}
			
		} catch (Exception e) {
			logger.info("Service method called using @ACTIVE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.INACTIVE_MEMBERS)
	@Operation(summary = "get inactive members details")
	public ResponseEntity<?> getInactiveMembers(@RequestParam("status") String status) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<MembersEntity> getInactiveMembers = dashboardService.getActiveMembers(status);
			apiResponse.setResponseCode(200);
			apiResponse.setCount(getInactiveMembers.size());
			apiResponse.setMessage("get inactive User successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(getInactiveMembers);
		} catch (Exception e) {
			logger.info("Service method called using @INACTIVE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.TOTAL_MEMBERS)
	@Operation(summary = "get total members details")
	public ResponseEntity<?> getTotalMembers() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<MembersEntity> getTotalMembers = dashboardService.getAllMembers();
			if(getTotalMembers.size()>0) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getTotalMembers.size());
				apiResponse.setMessage("get total Members successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getTotalMembers);
			}else {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getTotalMembers.size());
				apiResponse.setMessage("Not Found!");
				apiResponse.setErrorMessage(false);
			}
			
		} catch (Exception e) {
			logger.info("Service method called using @TOTAL_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.NEW_MEMBERS)
	@Operation(summary = "get new members details")
	public ResponseEntity<?> getNewMembers() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			Optional<MembersEntity> getNewMembers = dashboardService.getNewMembers();
			if(getNewMembers.isPresent()) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getNewMembers.get().getId());
				apiResponse.setMessage("get new members successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getNewMembers);	
			}else {
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("new new members added");
				apiResponse.setErrorMessage(false);
			}

		} catch (Exception e) {
			logger.info("Service method called using @NEW_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}

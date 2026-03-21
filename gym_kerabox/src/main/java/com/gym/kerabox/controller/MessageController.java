package com.gym.kerabox.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.entity.MessageEntity;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.MessageService;

@RestController
@RequestMapping(UserConstant.GYM_KERABOX)
public class MessageController {
	
	
	private static final Logger logger = Logger.getLogger(MessageController.class.getName());
	
	@Autowired
	MessageService messageService;
	
	
	@GetMapping(UserConstant.GET_ALL_MESSAGE_REQ)
	public ResponseEntity<?> getAllUsersMessage() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<MessageEntity> getMessage = messageService.getAllMessage();
			if(getMessage!=null && !getMessage.isEmpty()) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getMessage.size());
				apiResponse.setMessage("Get All Message successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getMessage);
			}else {
				apiResponse.setMessage("No Records Found .");
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			logger.info("Service method called using @GET_ALL_MESSAGE_REQ" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	@GetMapping(UserConstant.GET_LATEST_MESSAGE_REQ)
	public ResponseEntity<?> getLatesUsersMessage() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<MessageEntity> getMessage = messageService.getLatestMessage();
			if(getMessage!=null && !getMessage.isEmpty()) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getMessage.size());
				apiResponse.setMessage("Get Latest Message successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getMessage);
			}else {
				apiResponse.setMessage("No Records Found .");
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			logger.info("Service method called using @GET_LATEST_MESSAGE_REQ" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}

package com.gym.kerabox.controller;

/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.exceptionhandler.ErrorResponse;
import com.gym.kerabox.exceptionhandler.NoSuchUserExistsException;
import com.gym.kerabox.exceptionhandler.UserAlreadyExistsException;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.UserService;
import com.gym.kerabox.validator.UserValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(UserConstant.GYM_KERABOX)
@Tag(name = "User Controller", description = "User management APIs")
//@Slf4j
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	UserService userService;

	@PostMapping(UserConstant.SAVE_USER)
	@Operation(summary = "Create new user")
	public ResponseEntity<?> saveUserDetails(@RequestBody UserDto userDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {

			String userValidate = UserValidator.saveUserValidation(userDto);
			if (userValidate != null) {
				apiResponse.setResponseCode(200);
				apiResponse.setMessage(userValidate);
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}

			UserEntity checkedUser = userService.checkedUserAlreadyExist(userDto.getMobile(), userDto.getEmail());
			if (checkedUser != null) {
//				apiResponse.setCount(countUser);
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("dupliate data not allowed.");
//				apiResponse.setErrorMessage(false);
				apiResponse.setData(new UserEntity(checkedUser.getMobile(), checkedUser.getEmail()));
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			} else {
				UserEntity saveUser = userService.saveUser(userDto);
				Long countUser = userService.getUsetCount();
				apiResponse.setCount(countUser);
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("Data saved successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(saveUser);
			}

		} catch (Exception e) {
			logger.info("Service method called using @SAVE_USER" + e.getMessage());
			e.printStackTrace();
		}

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_ALL_USER)
	@Operation(summary = "Find all usres")
	public ResponseEntity<?> getAllUsers() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<UserEntity> getUser = userService.getUser();
			if (getUser != null && !getUser.isEmpty()) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getUser.size());
				apiResponse.setMessage("Get All User successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getUser);
			} else {
				apiResponse.setMessage("No Records Found .");
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.info("Service method called using @GET_ALL_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PutMapping(UserConstant.UPDATE_USER)
	@Operation(summary = "Update user details")
	public ResponseEntity<?> getUser(@RequestBody UserDto userDto) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			UserDto getUser = userService.updateUser(userDto);
			apiResponse.setResponseCode(200);
			apiResponse.setCount(getUser.getId());
			apiResponse.setMessage("Update User successfully.");
			apiResponse.setErrorMessage(false);
			apiResponse.setData(getUser);
		} catch (Exception e) {
			logger.info("Service method called using @UPDATE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(UserConstant.SEARCH_USER)
	@Operation(summary = "Search user by firstname,mobile,email")
	public ResponseEntity<?> searchUser(@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "email", required = false) String email) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			if (firstname != null && !firstname.isEmpty() || mobile != null && !mobile.isEmpty()
					|| email != null && !email.isEmpty()) {
				List<UserEntity> getUser = userService.searchUser(firstname, mobile, email);
				if (getUser != null && !getUser.isEmpty()) {
					apiResponse.setResponseCode(200);
					apiResponse.setMessage("User search successfully.");
					apiResponse.setErrorMessage(false);
					apiResponse.setData(getUser);
				} else {
					apiResponse.setMessage("No Records Found .");
					return new ResponseEntity<>(apiResponse, HttpStatus.OK);
				}

			} else {
				throw new RuntimeException(
						"Please provide at least one search criteria (firstname,mobile,email) to search for users");
			}

		} catch (Exception e) {
			logger.info("Service method called using @SEARCH_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping(UserConstant.DELETE_USER)
	@Operation(summary = "Delete user by id")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			if (id != 0) {
				userService.deleteUser(id);
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("User Id:" + id + " Deleted successfully.");
				apiResponse.setErrorMessage(false);
			} else {
				apiResponse.setResponseCode(200);
				apiResponse.setMessage("plese provide valid user id to delete user.");
				apiResponse.setErrorMessage(false);
			}

		} catch (Exception e) {
			logger.info("Service method called using @DELETE_USER" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ExceptionHandler(value = NoSuchUserExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoSuchUserExistsException(NoSuchUserExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoSuchElementException(NoSuchElementException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleCustomerAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
}

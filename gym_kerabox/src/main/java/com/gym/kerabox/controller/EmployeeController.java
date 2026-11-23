package com.gym.kerabox.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gym.kerabox.constant.UserConstant;
import com.gym.kerabox.entity.Employee;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	@Operation(summary = "upload kerabox employee data ")
	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {

		try {
			int count = employeeService.uploadExcel(file);

			return ResponseEntity.ok(Map.of("message", "Excel uploaded successfully", "totalRecordsSaved", count));

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
	
	
	@GetMapping(UserConstant.GET_ALL_EMPLOYEE)
	@Operation(summary = "Find all kerabox employee")
	public ResponseEntity<?> getAllEmployee() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<Employee> getAllEmployee = employeeService.getAllEmployee();
			if (getAllEmployee != null && !getAllEmployee.isEmpty()) {
				apiResponse.setResponseCode(200);
				apiResponse.setCount(getAllEmployee.size());
				apiResponse.setMessage("Get All User successfully.");
				apiResponse.setErrorMessage(false);
				apiResponse.setData(getAllEmployee);
			} else {
				apiResponse.setMessage("No Records Found .");
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.info("Service method called using @GET_ALL_Employee" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}

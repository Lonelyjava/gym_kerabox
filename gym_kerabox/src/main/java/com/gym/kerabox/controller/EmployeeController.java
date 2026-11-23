package com.gym.kerabox.controller;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gym.kerabox.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    
    

    public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}



	@PostMapping(
        value = "/upload",
        consumes = "multipart/form-data"
    )
	@Operation(summary = "upload kerabox employee data ")
    public ResponseEntity<?> uploadExcel(
            @RequestParam("file") MultipartFile file) {

        try {
            int count = employeeService.uploadExcel(file);

            return ResponseEntity.ok(
                Map.of(
                    "message", "Excel uploaded successfully",
                    "totalRecordsSaved", count
                )
            );

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }
}


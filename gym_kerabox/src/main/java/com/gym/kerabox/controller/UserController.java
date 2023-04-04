package com.gym.kerabox.controller;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.response.ApiResponse;
import com.gym.kerabox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gym_kerabox")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDto userDto){
        ApiResponse apiResponse = new ApiResponse();
        try{
            UserService.saveUser(userDto);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}

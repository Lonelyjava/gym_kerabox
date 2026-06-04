package com.gym.kerabox.service;

import java.util.List;

import com.gym.kerabox.dto.UsersDto;
import com.gym.kerabox.entity.User;

public interface UserService {

	User save(UsersDto user);
    List<User> findAll();
    User findOne(String username);
}

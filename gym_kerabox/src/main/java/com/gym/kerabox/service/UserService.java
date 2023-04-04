package com.gym.kerabox.service;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;

public interface UserService {
    public UserEntity saveUser(UserDto userDto) ;

}

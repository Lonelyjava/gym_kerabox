package com.gym.kerabox.serviceImpl;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserReposistory userReposistory;
    @Override
    public UserEntity saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        return userEntity;
    }
}

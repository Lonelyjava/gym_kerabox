package com.gym.kerabox.service;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;

import java.util.List;

public interface UserService {
	public UserEntity saveUser(UserDto userDto);
	
	public void saveEmp();

	public List<UserEntity> getUser();

	public List<UserEntity> searchUser();

	public List<UserEntity> deleteUser();

	public Long getUsetCount();

	public UserEntity checkedUserAlreadyExist(String mobile, String email);
}

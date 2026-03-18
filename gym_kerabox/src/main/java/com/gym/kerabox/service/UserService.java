package com.gym.kerabox.service;
import com.gym.kerabox.dto.SearchUserDto;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.UserEntity;

import java.util.List;

public interface UserService {
	public UserEntity saveUser(UserDto userDto);
	
	public void saveEmp();

	public List<UserEntity> getUser();

	public List<UserEntity> searchUser(SearchUserDto searchUserDto);

	public List<UserEntity> deleteUser();

	public Long getUsetCount();

	public UserEntity checkedUserAlreadyExist(String mobile, String email);

	public UserDto updateUser(UserDto userDto);
}

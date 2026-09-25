package com.gym.kerabox.service;
import com.gym.kerabox.dto.SearchUserDto;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import com.gym.kerabox.dto.MembersDto;
import com.gym.kerabox.entity.MembersEntity;

import java.util.List;

public interface MembersService {
	public MembersEntity saveUser(MembersDto userDto);
	
	public void saveEmp();

	public List<MembersEntity> getUser();

	public List<MembersEntity> searchUser(String firstname,String mobile,String email);

	public void deleteUser(long id);

	public Long getUsetCount();

	public MembersEntity checkedUserAlreadyExist(String mobile, String email);

	public MembersDto updateUser(MembersDto userDto);
}

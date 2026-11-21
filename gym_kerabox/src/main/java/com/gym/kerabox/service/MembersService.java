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
	public MembersEntity saveMembers(MembersDto userDto);
	

	public List<MembersEntity> getMembers();

	public List<MembersEntity> searchMembers(String firstname,String mobile,String email);

	public void deleteMembers(long id);

	public Long getUsetCount();

	public MembersEntity checkedMembersAlreadyExist(String mobile, String email);

	public MembersDto updateMembers(MembersDto userDto);
}

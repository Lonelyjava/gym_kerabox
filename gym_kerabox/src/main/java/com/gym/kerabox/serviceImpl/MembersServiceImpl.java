package com.gym.kerabox.serviceImpl;

/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.kerabox.controller.MembersController;
import com.gym.kerabox.dto.SearchUserDto;
import com.gym.kerabox.dto.MembersDto;
import com.gym.kerabox.entity.AddressEntity;
import com.gym.kerabox.entity.MembersEntity;
import com.gym.kerabox.reposistory.MembersReposistory;
import com.gym.kerabox.service.MembersService;

@Service
public class MembersServiceImpl implements MembersService {

	private static final Logger logger = Logger.getLogger(MembersController.class.getName());
	@Autowired
	MembersReposistory membersReposistory;


	@Override
	public MembersEntity saveMembers(MembersDto userDto) {
		MembersEntity userEntity = new MembersEntity();
		AddressEntity addressEntity = new AddressEntity();
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setGender(userDto.getGender());
		userEntity.setStatus(userDto.getStatus());
		userEntity.setMobile(userDto.getMobile());
		userEntity.setCreatedBy(LocalDate.now());
		userEntity.setLoggedInBy("kundan");
		userEntity.setUpdatedBy(LocalDate.now());
		addressEntity.setAddress1(userDto.getAddress1());
		addressEntity.setAddress2(userDto.getAddress2());
		addressEntity.setCreatedBy(LocalDate.now());
		addressEntity.setDistric(userDto.getDistric());
		addressEntity.setLoggedInBy("kudnan");
		addressEntity.setPincode(userDto.getPincode());
		addressEntity.setState(userDto.getState());
		addressEntity.setUpdatedBy(LocalDate.now());
		addressEntity.setUserEntity(userEntity);
		userEntity.setAddress(addressEntity);
		membersReposistory.save(userEntity);
		System.out.println("data save successfully.");
		return userEntity;
	}


	@Override
	public List<MembersEntity> getMembers() {
		return membersReposistory.findAll();
	}

	@Override
	public List<MembersEntity> searchMembers(String firstname, String mobile, String email) {
		List<MembersEntity> userList = null;
		if (firstname != null && !firstname.isEmpty() || mobile != null && !mobile.isEmpty()
				|| email != null && !email.isEmpty()) {
			userList = membersReposistory.searchUserByFirstnameOrMobileOrEmail(firstname, mobile, email);
		} else {

			throw new RuntimeException(
					"Please provide at least one search criteria (firstname,mobile,email) to search for users");
		}
		return userList;
	}

	@Override
	public void deleteMembers(long id) {
	 	membersReposistory.deleteById(id);
	}

	@Override
	public Long getUsetCount() {
		// TODO Auto-generated method stub
		return membersReposistory.count();
	}

	@Override
	public MembersEntity checkedMembersAlreadyExist(String mobile, String email) {
		// TODO Auto-generated method stub

		MembersEntity checkedUser = membersReposistory.checkUserAlreadyExist(mobile, email);
		return checkedUser;
	}

	@Override
	public MembersDto updateMembers(MembersDto userDto) {
		if (userDto != null) {
			MembersEntity userEntity = membersReposistory.findById(userDto.getId())
					.orElseThrow(() -> new RuntimeException("User not found with id :" + userDto.getId()));
			userEntity.setFirstname(userDto.getFirstname());
			userEntity.setLastname(userDto.getLastname());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setMobile(userDto.getMobile());
			userEntity.setGender(userDto.getGender());
			userEntity.setStatus(userDto.getStatus());
			userEntity.setUpdatedBy(LocalDate.now());
			userEntity.setLoggedInBy("kundan");
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setAddress1(userDto.getAddress1());
			addressEntity.setAddress2(userDto.getAddress2());
			addressEntity.setDistric(userDto.getDistric());
			addressEntity.setPincode(userDto.getPincode());
			addressEntity.setState(userDto.getState());
			addressEntity.setCreatedBy(LocalDate.now());
			addressEntity.setLoggedInBy("kundan");
			addressEntity.setUpdatedBy(LocalDate.now());
			userEntity.setAddress(addressEntity);
			membersReposistory.save(userEntity);
			logger.info("update user successfully @updateUser");

		}
		return userDto;
	}
}

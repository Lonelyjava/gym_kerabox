package com.gym.kerabox.serviceImpl;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.AddressEntity;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.UserService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserReposistory userReposistory;

	@Override
	public UserEntity saveUser(UserDto userDto)  {
		UserEntity userEntity = new UserEntity();
		AddressEntity addressEntity = new AddressEntity();
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		userEntity.setEmail(userDto.getEmail());
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
		userReposistory.save(userEntity);
		System.out.println("data save successfully.");
		return userEntity;
	}
}

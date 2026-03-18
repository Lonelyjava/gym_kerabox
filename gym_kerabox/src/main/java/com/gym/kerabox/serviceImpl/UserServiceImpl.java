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

import com.gym.kerabox.controller.UserController;
import com.gym.kerabox.dto.SearchUserDto;
import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.AddressEntity;
import com.gym.kerabox.entity.Employee;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.EmployeeReposistory;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserController.class.getName());
	@Autowired
	UserReposistory userReposistory;

	@Autowired
	EmployeeReposistory employeeReposistory;

	@Override
	public UserEntity saveUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
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
		userReposistory.save(userEntity);
		System.out.println("data save successfully.");
		return userEntity;
	}

	@Override
	public void saveEmp() {
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setCompany("tekmindz" + i);
			employee.setDept("CSE" + i);
			employee.setName("Kundan" + i);
			employee.setSalary(5000 + i);
			employeeReposistory.save(employee);
			System.out.println("saved emp details");
		}
	}

	@Override
	public List<UserEntity> getUser() {
		return userReposistory.findAll();
	}

	@Override
	public List<UserEntity> searchUser(SearchUserDto searchUserDto) {
		List<UserEntity> userList = null;
		if (searchUserDto.getFirstname() != null || searchUserDto.getMobile() != null
				|| searchUserDto.getEmail() != null) {
			userList = userReposistory.searchUserByFirstnameOrMobileOrEmail(searchUserDto.getFirstname(),
					searchUserDto.getMobile(), searchUserDto.getEmail());
		} else {

			throw new RuntimeException(
					"Please provide at least one search criteria (firstname,mobile,email) to search for users");
		}
		return userList;
	}

	@Override
	public List<UserEntity> deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getUsetCount() {
		// TODO Auto-generated method stub
		return userReposistory.count();
	}

	@Override
	public UserEntity checkedUserAlreadyExist(String mobile, String email) {
		// TODO Auto-generated method stub

		UserEntity checkedUser = userReposistory.checkUserAlreadyExist(mobile, email);
		return checkedUser;
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		if (userDto != null) {
			UserEntity userEntity = userReposistory.findById(userDto.getId())
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
			userReposistory.save(userEntity);
			logger.info("update user successfully @updateUser");

		}
		return userDto;
	}
}

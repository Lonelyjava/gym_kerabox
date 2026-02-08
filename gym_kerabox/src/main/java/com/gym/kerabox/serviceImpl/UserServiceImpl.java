package com.gym.kerabox.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.kerabox.dto.UserDto;
import com.gym.kerabox.entity.AddressEntity;
import com.gym.kerabox.entity.Employee;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.EmployeeReposistory;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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
		for(int i=0;i<10;i++) {
		Employee employee = new Employee();
		employee.setCompany("tekmindz"+i);
		employee.setDept("CSE"+i);
		employee.setName("Kundan"+i);
		employee.setSalary(5000+i);
		employeeReposistory.save(employee);
		System.out.println("saved emp details");
		}
	}

	@Override
	public List<UserEntity> getUser() {
		return userReposistory.findAll();
	}

	@Override
	public List<UserEntity> searchUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}
}

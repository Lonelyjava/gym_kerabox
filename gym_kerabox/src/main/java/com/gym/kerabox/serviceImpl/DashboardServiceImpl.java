package com.gym.kerabox.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.kerabox.dto.DashboardDto;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.DashboardService;


@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	UserReposistory userReposistory;
	@Override
	public List<UserEntity> getActiveUser() {
		// TODO Auto-generated method stub
		DashboardDto dashboardDto = new DashboardDto();
		String status ="active";
		List<UserEntity> userEntity = userReposistory.findByStatus(status);
		
		return userEntity;
	}

}

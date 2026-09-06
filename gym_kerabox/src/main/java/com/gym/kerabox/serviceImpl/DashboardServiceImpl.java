package com.gym.kerabox.serviceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.kerabox.dto.DashboardDto;
import com.gym.kerabox.entity.SystemLog;
import com.gym.kerabox.entity.UserEntity;
import com.gym.kerabox.reposistory.SystemLogRepository;
import com.gym.kerabox.reposistory.UserReposistory;
import com.gym.kerabox.service.DashboardService;


@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	UserReposistory userReposistory;
	
	 @Autowired
	 private SystemLogRepository repository;
	 
	@Override
	public List<UserEntity> getActiveUser(String status) {
		// TODO Auto-generated method stub
		DashboardDto dashboardDto = new DashboardDto();
		List<UserEntity> userEntity = userReposistory.findByStatus(status);
		saveServerIp();
		return userEntity;
	}
	
	public void saveServerIp() {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();

            SystemLog log = new SystemLog();
            log.setIpAddress(serverIp);
            log.setDescription("Application server startup IP tracking");

            repository.save(log);
            System.out.println("serverIp"+serverIp +"saved in db");
        } catch (UnknownHostException e) {
            e.printStackTrace(); 
        }
    }

}

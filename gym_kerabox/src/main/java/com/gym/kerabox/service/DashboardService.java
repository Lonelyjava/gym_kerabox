package com.gym.kerabox.service;

import java.util.List;

import com.gym.kerabox.dto.DashboardDto;
import com.gym.kerabox.entity.MembersEntity;

public interface DashboardService {

	 public List<MembersEntity> getActiveUser(String status);

}

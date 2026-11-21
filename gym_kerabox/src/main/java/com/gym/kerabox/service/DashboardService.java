package com.gym.kerabox.service;

import java.util.List;
import java.util.Optional;

import com.gym.kerabox.entity.MembersEntity;

public interface DashboardService {

	 public List<MembersEntity> getActiveMembers(String status);

	 public List<MembersEntity> getAllMembers();

	 public Optional<MembersEntity> getNewMembers();

}

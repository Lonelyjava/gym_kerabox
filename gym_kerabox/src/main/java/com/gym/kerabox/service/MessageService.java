package com.gym.kerabox.service;

import java.util.List;

import com.gym.kerabox.entity.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getAllMessage();

	public List<MessageEntity> getLatestMessage();

}

package com.gym.kerabox.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.kerabox.entity.MessageEntity;
import com.gym.kerabox.reposistory.MessageReposistory;
import com.gym.kerabox.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageReposistory messageReposistory;
	
	@Override
	public List<MessageEntity> getAllMessage() {
		List<MessageEntity>  messageEntity=messageReposistory.findAll();
		return messageEntity;
	}

	@Override
	public List<MessageEntity> getLatestMessage() {
		LocalDate localDate = LocalDate.now();
		List<MessageEntity>  messageEntity=messageReposistory.findByCreatedDate(localDate);
		return messageEntity;
	}

}

package com.gym.kerabox.reposistory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.entity.MessageEntity;

@Repository
public interface MessageReposistory extends JpaRepository<MessageEntity,Long> {

	List<MessageEntity> findByCreatedDate(LocalDate localDate);

}

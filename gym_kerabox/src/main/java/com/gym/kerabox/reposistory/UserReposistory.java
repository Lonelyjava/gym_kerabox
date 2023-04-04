package com.gym.kerabox.reposistory;

import com.gym.kerabox.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposistory extends JpaRepository<UserEntity,Long> {
}

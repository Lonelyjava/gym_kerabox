package com.gym.kerabox.reposistory;
import java.util.List;

/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.entity.MembersEntity;

@Repository
public interface MembersReposistory extends JpaRepository<MembersEntity,Long> {

	@Query("select u from UserEntity u where u.mobile=:mobile or u.email=:email")
	public MembersEntity checkUserAlreadyExist(@Param("mobile")String mobile,@Param("email") String email);

	public List<MembersEntity> searchUserByFirstnameOrMobileOrEmail(String firstname, String mobile, String email);

	public List<MembersEntity> findByStatus(String status);
}

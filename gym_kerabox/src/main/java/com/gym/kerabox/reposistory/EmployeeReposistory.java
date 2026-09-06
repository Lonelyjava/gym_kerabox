package com.gym.kerabox.reposistory;
/**
 * UserController class for managing users.
 *
 * @author Kundan Kumar
 * @version 1.0
 * @since 2026-03-17
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.entity.Employee;

@Repository
public interface EmployeeReposistory extends JpaRepository<Employee, Long>{

}

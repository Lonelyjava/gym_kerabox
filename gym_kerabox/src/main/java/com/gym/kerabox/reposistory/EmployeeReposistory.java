package com.gym.kerabox.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.entity.Employee;

@Repository
public interface EmployeeReposistory extends JpaRepository<Employee, Long>{

}

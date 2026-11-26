package com.gym.kerabox.redis.reposistory;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.redis.entity.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	Optional<Customer> findById(String valueOf);

	void deleteById(String valueOf);

    // Basic CRUD operations
}

package com.gym.kerabox.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.redis.entity.Customer;
import com.gym.kerabox.redis.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer Controller", description = "Redis Customer data store")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/save")
	@Operation(summary = "Customer data saved in redis")
	public Customer addCustomer(@RequestBody Customer customer) {

		return service.addCustomer(customer);
	}

	@GetMapping("/getAllCustomer")
	@Operation(summary = "Getting records from Redis")
	public List<Customer> getListOfCustomers() {

		return service.getAllCustomers();
	}

	@GetMapping("/getCustomerbyId/{id}")
	@Operation(summary = "Getting records of customer from Redis")
	public Customer getCustomer(@PathVariable int id) {

		return service.getCustomerById(id);
	}

	@PutMapping("updateCustomer/{id}")
	@Operation(summary = "Updating records of customer in Redis ")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer newCustomer) {

		return service.updateCustomerById(id, newCustomer);
	}

	@DeleteMapping("deleteCustomer/{id}")
	@Operation(summary = "Deleting records of customer of Redis")
	public String deleteCustomer(@PathVariable int id) {

		service.deleteCustomerById(id);
		return "Customer Deleted Successfully";
	}
}
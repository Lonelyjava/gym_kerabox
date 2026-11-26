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

@RestController
@RequestMapping("/customer")
public class CustomerController {

      // Inject the CustomerService dependency into class
    @Autowired 
      private CustomerService service;

      // to insert new customer data into the Redis database.
    @PostMapping("/save")
    public Customer addCustomer(@RequestBody Customer customer){

        return service.addCustomer(customer);
    }

      // to fetch All the customers from the Redis database
    @GetMapping("/getAllCustomer")
      public List<Customer> getListOfCustomers(){
   
        return service.getAllCustomers();
    }

      // to fetch Customer data using ID from Redis Database
    @GetMapping("/getCustomerbyId/{id}")
    public Customer getCustomer(@PathVariable int id){
      
        return service.getCustomerById(id);
    }

      // to update an existing customer in the Redis database using ID.
    @PutMapping("updateCustomer/{id}")
    public Customer
    updateCustomer(@PathVariable int id,
                   @RequestBody Customer newCustomer){
      
        return service.updateCustomerById(id, newCustomer);
    }

      // to delete an existing customer from the Redis database using ID
    @DeleteMapping("deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id){
      
        service.deleteCustomerById(id);
        return "Customer Deleted Successfully";
    }
}
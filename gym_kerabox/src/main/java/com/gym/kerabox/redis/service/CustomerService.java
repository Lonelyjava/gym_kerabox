package com.gym.kerabox.redis.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.gym.kerabox.auth.security.SecurityConfiguration;
import com.gym.kerabox.redis.entity.Customer;
import com.gym.kerabox.redis.reposistory.CustomerRepo;

@Service
public class CustomerService {

    private final SecurityConfiguration securityConfiguration;

      // Injecting Repository into service class
    @Autowired 
      private CustomerRepo repo;
    
    public CustomerService(
            RedisIdService redisIdService, SecurityConfiguration securityConfiguration) {
this.redisIdService = redisIdService;
this.securityConfiguration = securityConfiguration;
}
  
   
   private final RedisIdService redisIdService ;


      // to insert new customer data into the Redis database
    public Customer addCustomer(Customer customer){
    	
    	
    	if (customer.getId() == 0) {
            customer.setId(redisIdService.generateId("Customer:ID"));
        }

//    	System.out.println("redis id:"+redisIdService.generateId("Customer:ID"));
        return repo.save(customer);
    }
    
   

      // run a fetch query in the Redis Database
      // to get a list of all the customers
    public List<Customer> getAllCustomers(){

        List<Customer> allCustomer = new ArrayList<>();
        repo.findAll().forEach(allCustomer::add);
        return allCustomer;
    }

      // fetch operation to get customer using an ID
    public Customer getCustomerById(int id){

        Optional<Customer> optionalCustomer
            = repo.findById(String.valueOf(id));
        return optionalCustomer.orElse(null);
    }

      
      // update operation to existing customer using an ID
    public Customer updateCustomerById(int id,
                                       Customer newCustomer){
      
        Optional<Customer> existingCustomer
            = repo.findById(String.valueOf(id));

        if (existingCustomer.isPresent()) {
            Customer updatedCustomer
                = existingCustomer.get();

            updatedCustomer.setName(newCustomer.getName());
            updatedCustomer.setPhone(newCustomer.getPhone());
            updatedCustomer.setEmail(newCustomer.getEmail());

            repo.deleteById(String.valueOf(id));
            return repo.save(updatedCustomer);
        }

        return null;
    }

      // delete the existing customer
    public void deleteCustomerById(int id){
        repo.deleteById(String.valueOf(id));
    }
}

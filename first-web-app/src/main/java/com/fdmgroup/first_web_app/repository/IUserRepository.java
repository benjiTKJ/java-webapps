package com.fdmgroup.first_web_app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.first_web_app.model.Customer;


/**
 * UserRepository interface to query and return customer information
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Repository
public interface IUserRepository extends JpaRepository<Customer, Integer> {

	/**
	 * Method with optional return value to find user by their username
	 * @param username
	 * @return Optional<Customer>
	 */
	Optional<Customer> findByUsername(String username);
	
	/**
	 * Method to query id of customer based on their username
	 * @param username
	 * @return int
	 */
	@Query("SELECT e.id FROM Customer e WHERE e.username = :username")
	int getIdOfCustomer(@Param("username") String username);

	

}

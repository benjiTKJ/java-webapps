package com.fdmgroup.first_web_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.first_web_app.model.Customer;
import com.fdmgroup.first_web_app.model.RegistrationForm;
import com.fdmgroup.first_web_app.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * User service class to handle request from userController and send to userRepository
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Service
@Slf4j
public class UserService {
	
	@Autowired
	IUserRepository userRepository;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	/**
	 * Constructor for testing
	 * @param userRepository
	 */
	public UserService(IUserRepository userRepository) {
		this.userRepository=userRepository;
	}

	/**
	 * Method to verify if comfirm password matches password
	 * @param registrationForm
	 * @return boolean
	 */
	public boolean verifyComfirmPassword(RegistrationForm registrationForm) {
		log.info("Is comfirm password same as password? : "+ registrationForm.getPassword().equals(registrationForm.getComfirmPassword()));
		return registrationForm.getPassword().equals(registrationForm.getComfirmPassword());
	}
	
	/**
	 * Method to verify if username exist by calling to userRepository to check 
	 * @param registrationForm
	 * @return boolean
	 */
	public boolean verifyIfUsernameExist(RegistrationForm registrationForm) {
		Optional<Customer> duplicateUsername = userRepository.findByUsername(registrationForm.getUsername());
		if(duplicateUsername.isPresent()) {
			log.info("Username does not exist");
			return false;
		}else {
			log.info("Username exist");
			return true;
		}
	}
	
	
	
	/**
	 * Method to save customer details into the database if various checks are correct
	 * @param registrationForm
	 * @return boolean
	 */
	public boolean registerUser(RegistrationForm registrationForm) {
		
		verifyComfirmPassword(registrationForm);
		if(verifyComfirmPassword(registrationForm) && verifyIfUsernameExist(registrationForm)) {
			userRepository.save(new Customer(registrationForm.getFirstname(),registrationForm.getLastname(),registrationForm.getUsername(),bCryptPasswordEncoder.encode(registrationForm.getPassword()),registrationForm.getEmail()));
			log.info("Registered user : "+ registrationForm.getUsername());
			return true;
		}else {
			log.error("user not registered");
			return false;
		}
		
	}

	/**
	 * Method to allow or deny user login via checks
	 * @param customer
	 * @return boolean
	 */
	public boolean loginUser(Customer customer) {
		Optional<Customer> foundCustomerOptional = userRepository.findByUsername(customer.getUsername());
		if(foundCustomerOptional.isPresent()) {
			Customer foundCustomer = foundCustomerOptional.get();
			log.info("Found customer:" + foundCustomer.getUsername());
			return bCryptPasswordEncoder.matches(customer.getPassword(), foundCustomer.getPassword());
		}else 
		log.error("Customer not found");	
		return false ;
	
	}

	/**
	 * Method to check if current session has a authenticated user 
	 * @param sessionUsername
	 * @return boolean
	 */
	public boolean authenticateUser(String sessionUsername) {
		log.info("Current session by user: " + userRepository.findByUsername(sessionUsername));
		return userRepository.findByUsername(sessionUsername).isPresent()?true:false;
	}
	
	
	
	
	
}

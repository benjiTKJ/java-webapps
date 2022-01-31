package com.benjamin.CryptoEx.service;

import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.benjamin.CryptoEx.model.CustomerDetail;
import com.benjamin.CryptoEx.model.RegistrationForm;
import com.benjamin.CryptoEx.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	IUserRepository userRepository;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public boolean signupCustomer(RegistrationForm registrationForm) {
		if(verifyComfirmPassword(registrationForm) && verifyIfUsernameExist(registrationForm)&& verifyEmailValidity(registrationForm)) {
			userRepository.save(new CustomerDetail(registrationForm.getFirstName(),registrationForm.getLastName(),registrationForm.getUsername(),bCryptPasswordEncoder.encode(registrationForm.getPassword()),registrationForm.getEmail(),registrationForm.getNumber()));
			System.out.println("User :"+registrationForm.getUsername()+" registered and saved");
			return true;
		}else {
			System.out.println("User not registered");
			return false;
		}
	}

	private boolean verifyEmailValidity(RegistrationForm registrationForm) {
		String email = registrationForm.getEmail();
		if(EmailValidator.getInstance().isValid(email)) {
			System.out.println("Email is valid");
			return true;
		}else {
			System.out.println("Email invalid");
			return false;
		}
		
		
			
		
	}

	private boolean verifyIfUsernameExist(RegistrationForm registrationForm) {
		System.out.println("Is username unique?> "+!userRepository.findByUsername(registrationForm.getUsername()).isPresent());
		return !userRepository.findByUsername(registrationForm.getUsername()).isPresent();
	}

	private boolean verifyComfirmPassword(RegistrationForm registrationForm) {
		System.out.println("Did password match?> "+registrationForm.getPassword().equals(registrationForm.getComfirmPassword()));
		return registrationForm.getPassword().equals(registrationForm.getComfirmPassword());
	}

	public boolean loginCustomer(CustomerDetail customerDetail) {
		Optional<CustomerDetail> foundCustomerDetail = userRepository.findByUsername(customerDetail.getUsername());
		if(foundCustomerDetail.isPresent()) {
			CustomerDetail foundCustomer = foundCustomerDetail.get();
			System.out.println("Found customer: "+foundCustomer);
			return bCryptPasswordEncoder.matches(customerDetail.getPassword(), foundCustomer.getPassword());
		}else {
			System.out.println("Customer not found");
			return false;
		}
		
	}

	public boolean authenticateUser(String username) {
		System.out.println("Current session by user: "+ userRepository.findByUsername(username));
		return userRepository.findByUsername(username).isPresent()?true:false;
	}

	public int retriveId(String username) {
		
		return userRepository.getIdOfCustomer(username);
	}
	
}

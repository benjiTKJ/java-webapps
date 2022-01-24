package com.fdmgroup.first_web_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.first_web_app.model.Customer;
import com.fdmgroup.first_web_app.model.Input;
import com.fdmgroup.first_web_app.repository.IInputRepository;
import com.fdmgroup.first_web_app.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Input service class to call into the inputRepository and userRepository 
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Service
@Slf4j
public class InputService {

	@Autowired
	IInputRepository inputRepository;
	@Autowired
	IUserRepository userRepository;
	
	/**
	 * Constructor for testing
	 * @param userRepository
	 * @param inputRepository
	 */
	public InputService(IUserRepository userRepository, IInputRepository inputRepository) {
		this.userRepository=userRepository;
		this.inputRepository=inputRepository;
	}

	/**
	 * Method to add input of customer and save into the database
	 * @param input
	 * @param customerId
	 */
	public void addInput(Input input, int customerId) {
		input.getLocalDate();
		log.info(input.getLocalDate() + " here is the date ");
		if (userRepository.findById(customerId) != null) {
			Optional<Customer> customerDetails = userRepository.findById(customerId);
			if (customerDetails.isPresent()) {
				Customer foundCustomerDetails = customerDetails.get();
				input.setCustomer(foundCustomerDetails);
				inputRepository.save(new Input(input.getCategory(), input.getDescription(), input.getAmount(),
						input.getLocalDate(), input.getCustomer()));
				log.info(input.getCategory(), input.getDescription(), input.getAmount(),
						input.getLocalDate(), input.getCustomer());
			}

		}

	}

	/**
	 * Method to call to inputRepository to get all input via customer id and return to input controller
	 * @param id
	 * @return List<Input>
	 */
	public List<Input> displayInput(int id) {
		log.info("List of input "+inputRepository.findAllInputById(id));
		return inputRepository.findAllInputById(id);

	}

	/**
	 * Method to call to inputRepository to find the input by the searchWord and return to the input controller 
	 * @param searchWord
	 * @param id
	 * @return List<Input>
	 */
	public List<Input> searchInput(String searchWord, int id) {
		log.info("List of input found "+inputRepository.findAllInputBySearchWord(searchWord,id));
		return inputRepository.findAllInputBySearchWord(searchWord,id);
	}

	/**
	 * Method to deleteInput based on input id
	 * @param id
	 */
	public void deleteInput(int id) {
		log.info("deleted input id: "+ id);
		inputRepository.deleteById(id);
		
		
	}

	
	
	
	
	

}

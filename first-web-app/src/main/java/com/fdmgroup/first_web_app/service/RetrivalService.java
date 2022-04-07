package com.fdmgroup.first_web_app.service;

import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.first_web_app.repository.IInputRepository;
import com.fdmgroup.first_web_app.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;
/**
 * Retrival service class to process retrival of data and calculation 
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Service
@Slf4j
public class RetrivalService {

	@Autowired
	IInputRepository inputRepository;
	@Autowired
	IUserRepository userRepository;

	/**
	 * Constructor for Testing
	 * @param userRepository
	 * @param inputRepository
	 */
	public RetrivalService(IUserRepository userRepository, IInputRepository inputRepository) {
		this.userRepository=userRepository;
		this.inputRepository=inputRepository;
	}

	/**
	 * Method to call to userRepository to retrive id of customer based on 
	 * their username to pass to inputController
	 * @param username
	 * @return int
	 */
	public int retriveId(String username) {
		log.info("id of customer : "+userRepository.getIdOfCustomer(username));
		return userRepository.getIdOfCustomer(username);

	}

	/**
	 * Method to call to inputRepository to retrive all expenses based on customer id and return to input controller
	 * @param income
	 * @param id
	 * @return double
	 */
	public double displayExpense(String income, int id) {
		Optional<Double> findExpenseOptional = inputRepository.findByExpenseOptional(income, id);
		if (findExpenseOptional.isPresent()) {
			log.info("total expense $:"+inputRepository.findByExpense(income, id));
			return inputRepository.findByExpense(income, id);
		} else {
			log.info("no expense found");
			return 0;
		}
	}

	/**
	 * Method to call to inputRepository to retrive all income based on customer id and return to input controller
	 * @param category
	 * @param id
	 * @return double
	 */
	public double displayCategory(String category, int id) {
		Optional<Double> findCategoryOptional = inputRepository.findByCategoryOptional(category, id);
		if (findCategoryOptional.isPresent()) {
			log.info("total amount by "+ category + " :$"+inputRepository.findByCategory(category, id));
			return inputRepository.findByCategory(category, id);
		} else {
			log.info("no "+category+" found");
			return 0;
		}
	}

	/**
	 * Method to call to inputRepository to retrive monthly savings(income-expenses) 
	 * based on selected month & year and return to inputController
	 * @param month
	 * @param year
	 * @param id
	 * @return double
	 */
	public double displaySavingsMonthly(int month, int year, int id) {

		String income = "income";
		Optional<Double> findCategoryOptional = inputRepository.findByCategoryMonthOptional(month, year, income, id);
		Optional<Double> findExpenseOptional = inputRepository.findByExpenseMonthOptional(month, year, income, id);
		if (findCategoryOptional.isPresent() && findExpenseOptional.isPresent()) {
			double totalIncome = inputRepository.findByCategoryMonth(month, year, income, id);
			double totalExpense = inputRepository.findByExpenseMonth(month, year, income, id);
			log.info("total savings : $"+ (totalIncome - totalExpense) );
			return totalIncome - totalExpense;
		} else if (findCategoryOptional.isPresent() && findExpenseOptional.isEmpty()) {
			double totalIncome = inputRepository.findByCategoryMonth(month, year, income, id);
			log.info("total savings : $"+totalIncome);
			return totalIncome;
		} else if (findCategoryOptional.isEmpty() && findExpenseOptional.isPresent()) {
			double totalExpense = inputRepository.findByExpenseMonth(month, year, income, id);
			log.info("total savings : -$"+totalExpense);
			return -totalExpense;
		} else {
			return 0;
		}
	}

	/**
	 * Method to return the sum of a specific category based on a selected month by calling to the inputRepository
	 * @param month
	 * @param year
	 * @param category
	 * @param id
	 * @return double
	 */
	public double displayCategoryByMonth(int month, int year, String category, int id) {
		Optional<Double> findCategoryOptional = inputRepository.findByCategoryMonthOptional(month, year, category, id);
		if (findCategoryOptional.isPresent()) {
			log.info("total "+ category + " by " + month + "/" + year + ": $" + inputRepository.findByCategoryMonth(month, year, category, id));
			return inputRepository.findByCategoryMonth(month, year, category, id);
		} else {
			log.info("total "+ category +" by " + month + "/" + year + ": $0");
			return 0;
		}
		
	}

	/**
	 * Method to call to the inputRepository to find the totalExpenses by month selected
	 * @param month
	 * @param year
	 * @param income
	 * @param id
	 * @return double 
	 */
	public double displayExpenseByMonth(int month, int year, String income, int id) {
		Optional<Double> findExpenseOptional = inputRepository.findByExpenseMonthOptional(month, year, income, id);
		if (findExpenseOptional.isPresent()) {
			log.info("total expense "+" by "+ month +"/"+year+" : $"+inputRepository.findByExpenseMonth(month, year, income, id));
			return inputRepository.findByExpenseMonth(month, year, income, id);
		}else {
			log.info("total expense "+" by "+ month +"/"+year+" : $0");
			return 0;
		}
		
	}

	


}

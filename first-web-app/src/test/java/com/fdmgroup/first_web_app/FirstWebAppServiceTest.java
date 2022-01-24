package com.fdmgroup.first_web_app;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.first_web_app.model.Customer;
import com.fdmgroup.first_web_app.model.Input;
import com.fdmgroup.first_web_app.repository.IInputRepository;
import com.fdmgroup.first_web_app.repository.IUserRepository;
import com.fdmgroup.first_web_app.service.InputService;
import com.fdmgroup.first_web_app.service.RetrivalService;
import com.fdmgroup.first_web_app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class FirstWebAppServiceTest {

	UserService userService;
	InputService inputService;
	RetrivalService retrivalService;
	
	@Mock
	IInputRepository inputRepository;
	@Mock
	IUserRepository userRepository;
	
	@Mock
	Input input;
	@Mock
	Customer customer;
	
	String searchWord = null;
	String username = null;
	String income = null;
	String category = null;
	
	
	@BeforeEach
	public void init() {
		inputService = new InputService(userRepository,inputRepository);
		retrivalService = new RetrivalService(userRepository,inputRepository);
		userService = new UserService(userRepository);
	}
	
	@Test
	public void addInput_findById_WhenCalled() {
		inputService.addInput(input, 1);
		verify(userRepository, times(2)).findById(1);
	}
	
	@Test
	public void displayInput_findAllInputById_WhenCalled() {
		inputService.displayInput(1);
		verify(inputRepository, times(2)).findAllInputById(1);
	}
	
	@Test
	public void searchInput_findAllInputBySearchWord_WhenCalled() {
		inputService.searchInput(searchWord, 1);
		verify(inputRepository, times(2)).findAllInputBySearchWord(searchWord, 1);
	}
	
	@Test
	public void deleteInput_deleteById_WhenCalled() {
		inputService.deleteInput(1);
		verify(inputRepository, times(1)).deleteById(1);
	}
	
	@Test
	public void retriveId_getIdOfCustomer_WhenCalled() {
		retrivalService.retriveId(username);
		verify(userRepository, times(2)).getIdOfCustomer(username);
	}
	
	@Test
	public void displayExpense_findByExpenseOptional_WhenCalled() {
		retrivalService.displayExpense(income, 1);
		verify(inputRepository, times(1)).findByExpenseOptional(income, 1);
	}
	
	@Test
	public void displayCategory_findByCategoryOptional_WhenCalled() {
		retrivalService.displayCategory(category, 1);
		verify(inputRepository, times(1)).findByCategoryOptional(category, 1);
	}
	
	@Test
	public void displaySavingsMonthly_findByCategoryMonthOptional_WhenCalled() {
		retrivalService.displaySavingsMonthly(1, 1, 1);
		verify(inputRepository, times(1)).findByCategoryMonthOptional(1, 1, "income", 1);
	}
	
	@Test
	public void displaySavingsMonthly_findByExpenseMonthOptional_WhenCalled() {
		retrivalService.displaySavingsMonthly(1, 1, 1);
		verify(inputRepository, times(1)).findByExpenseMonthOptional(1, 1, "income", 1);
	}
	
	@Test
	public void displayCategoryByMonth_findByCategoryMonthOptional_WhenCalled() {
		retrivalService.displayCategoryByMonth(1, 1, category, 1);
		verify(inputRepository, times(1)).findByCategoryMonthOptional(1, 1, category, 1);
	}
	
	@Test
	public void displayExpenseByMonth_findByExpenseMonthOptional_WhenCalled() {
		retrivalService.displayExpenseByMonth(1, 1, income, 1);
		verify(inputRepository, times(1)).findByExpenseMonthOptional(1, 1, income, 1);
	}
	
	@Test
	public void loginUser_findByUsername_WhenCalled() {
		userService.loginUser(customer);
		verify(userRepository, times(1)).findByUsername(username);
	}
	
	@Test
	public void authenticateUser_findByUsername_WhenCalled() {
		userService.authenticateUser(username);
		verify(userRepository, times(2)).findByUsername(username);
	}
	
	
	
	
}

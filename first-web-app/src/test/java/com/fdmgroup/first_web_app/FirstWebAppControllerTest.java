package com.fdmgroup.first_web_app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.fdmgroup.first_web_app.controller.InputController;
import com.fdmgroup.first_web_app.controller.UserController;
import com.fdmgroup.first_web_app.model.Input;
import com.fdmgroup.first_web_app.model.RegistrationForm;
import com.fdmgroup.first_web_app.service.InputService;
import com.fdmgroup.first_web_app.service.RetrivalService;
import com.fdmgroup.first_web_app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class FirstWebAppControllerTest {

	
	InputController inputController;
	UserController userController;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	Model model;
	
	@Mock
	RegistrationForm registrationForm;
	
	@Mock
	Input input;
	
	@Mock
	InputService inputService;
	@Mock
	UserService userService;
	@Mock
	RetrivalService retrivalService;
	
	@BeforeEach
	public void init() {
		inputController = new InputController(inputService);
		userController = new UserController(userService);
	}
	
	
	@Test
	public void deleteInput_deleteInput_WhenCalled() {
		inputController.deleteInput(1);
		verify(inputService,times(1)).deleteInput(1);
	}
	
	@Test
	public void deleteRecentInput_deleteInput_WhenCalled() {
		inputController.deleteRecentInput(1);
		verify(inputService, times(1)).deleteInput(1);
	}
	
	@Test
	public void validateRegister_registerUser_WhenCalled() {
		userController.validateRegister(model, registrationForm);
		verify(userService, times(1)).registerUser(registrationForm);
	}
	
	@Test
	public void deleteInput_Returns_searchInput() {
		assertEquals("searchInput", inputController.deleteInput(1));
	}
	
	@Test
	public void deleteRecentInput_Returns_redirectloginProfile(){
		assertEquals("redirect:loginProfile", inputController.deleteRecentInput(1));
	}
	
	@Test
	public void goToIndex_Returns_index() {
		assertEquals("index", userController.goToIndex());
	}
	
	@Test
	public void goToLogin_Returns_login() {
		assertEquals("login", userController.goToLogin());
	}
	
	@Test
	public void goToRegister_Returns_register() {
		assertEquals("register", userController.goToRegister());
	}
	
	@Test
	public void validateRegister_Returns_register() {
		userService.registerUser(registrationForm);
		assertEquals("register", userController.validateRegister(model, registrationForm));
	}
	
	
}

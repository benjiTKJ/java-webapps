package com.fdmgroup.first_web_app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.first_web_app.model.Customer;
import com.fdmgroup.first_web_app.model.RegistrationForm;
import com.fdmgroup.first_web_app.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller class to handle Customer registration and logging in/out
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Controller
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	

	public UserController(UserService userService) {
		this.userService=userService;
	}

	/**
     * Method to direct customer to the index page when clicked
     * @return String
     */
	@RequestMapping("index")
	public String goToIndex() {
		log.info("Going to index page");
		return "index";
	}
	
	/**
	 * Method to direct customer to the login page when clicked on login
	 * @return String
	 */
	@RequestMapping("login")
	public String goToLogin() {
		log.info("Going to log in page");
		return "login";
	}
	
	/**
	 * Method to direct customer to register page when clicked on register
	 * @return String
	 */
	@RequestMapping("register")
	public String goToRegister() {
		log.info("Going to register page");
		return "register";
	}
	
	/**
	 * Method to register customer 
	 * @param model
	 * @param registrationForm
	 * @return String
	 */
	@PostMapping("registerUser")
	public String validateRegister(Model model, RegistrationForm registrationForm) {
		log.info("Registering user");
		model.addAttribute("errorMessage",null);
		
		if(userService.registerUser(registrationForm)) {
			return "login";
		}else {
			model.addAttribute("errorMessage","Invalid Credentials");
			return "register";
		}
	
	}
	
	/**
	 * Method to login customer 
	 * @param request
	 * @param model
	 * @param customer
	 * @return String
	 */
	@RequestMapping("loginUser")
	public String goToProfilePage(HttpServletRequest request, Model model, Customer customer) {
		log.info("Going to profile page");
		model.addAttribute("errorMessage",null);
		if(userService.loginUser(customer)) {
		request.getSession().setAttribute("username", customer.getUsername());
		request.getSession().setAttribute("name", customer.getLastName());
		return "redirect:loginProfile";
		}else {
			model.addAttribute("errorMessage","Invalid Credentials");
			return "login";
		}
	}
	
	/**
	 * Method to logout customer 
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		log.info("Logging out");
		return "index";
	}
	
}

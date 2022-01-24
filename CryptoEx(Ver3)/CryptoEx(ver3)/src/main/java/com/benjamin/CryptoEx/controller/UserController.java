package com.benjamin.CryptoEx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.benjamin.CryptoEx.model.CustomerDetail;
import com.benjamin.CryptoEx.model.RegistrationForm;
import com.benjamin.CryptoEx.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("index")
	public String goToIndex() {
		System.out.println("Going to index page");
		return "index";
	}
	
	@RequestMapping("signup")
	public String goToSignup() {
		System.out.println("Going to signup page");
		return "signup";
	}
	
	@PostMapping("signupCustomer")
	public String signupCustomer(Model model, RegistrationForm registrationForm) {
		System.out.println("Registering user");
		model.addAttribute("errorMessage",null);
		
		if(userService.signupCustomer(registrationForm)) {
			return "index";
		}else {
			model.addAttribute("errorMessage","Invalid Credentials");
			return "signup";
		}
		
	}
	
	@RequestMapping("loginCustomer")
	public String loginCustomer(HttpServletRequest httpServletRequest, Model model, CustomerDetail customerDetail) {
		
		model.addAttribute("errorMessage",null);
		if(userService.loginCustomer(customerDetail)) {
			httpServletRequest.getSession().setAttribute("username", customerDetail.getUsername());
			httpServletRequest.getSession().setAttribute("lastName", customerDetail.getLastName());
			return "redirect:customerAccount";
		}else {
			model.addAttribute("errorMessage","Invalid Credentials");
			return "index";
		}
	}
	
	@RequestMapping("logout")
	public String logoutCustomer(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("Logging out");
		return "index";
	}
}

package com.benjamin.CryptoEx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.benjamin.CryptoEx.service.AccountService;
import com.benjamin.CryptoEx.service.UserService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	public String getUsernameOfCustomer(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		return username;
	}
	
	public int getIdOfCustomer(HttpServletRequest request) {
		String username = getUsernameOfCustomer(request);
		int id = accountService.retriveId(username);
		return id;
	}
	
	@RequestMapping("customerAccount")
	public String goToCustomerAccount(HttpServletRequest request, Model model) {
		String username = getUsernameOfCustomer(request);
		model.addAttribute("helloMessage", username);
		return userService.authenticateUser(username) ?"customerAccount":"error";
	}
	
	@RequestMapping("loginIndex")
	public String goToLoginIndex(HttpServletRequest request) {
		String username = getUsernameOfCustomer(request);
		return userService.authenticateUser(username)?"loginIndex":"error";
	}
	
}

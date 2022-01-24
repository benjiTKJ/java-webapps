package com.fdmgroup.first_web_app.controller;

import java.text.DateFormat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.first_web_app.enumerator.InputCategory;
import com.fdmgroup.first_web_app.model.Input;
import com.fdmgroup.first_web_app.service.InputService;
import com.fdmgroup.first_web_app.service.RetrivalService;
import com.fdmgroup.first_web_app.service.UserService;

import lombok.extern.slf4j.Slf4j;


/**
 * Input controller class to handle customer's input (CRUD)
 * 
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT
 * 
 */
@Controller
@Slf4j
public class InputController {

	@Autowired
	InputService inputService;
	@Autowired
	UserService userService;
	@Autowired
	RetrivalService retrivalService;

	public InputController(InputService inputService) {
		this.inputService=inputService;
	}

	/**
	 * Method to allow customer to add input and display input
	 * 
	 * @param request
	 * @param model
	 * @param input
	 * @return String
	 * @throws ParseException
	 */
	@PostMapping("addInput")
	public String addNewInputandViewInput(HttpServletRequest request, Model model, Input input) throws ParseException {
		log.info("Adding new input");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat.format(input.getLocalDate()));
		input.setLocalDate(newDate);

		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);
		model.addAttribute("helloMessage", username);
		inputService.addInput(input, id);

		log.info("adding input", input);
		model.addAttribute("inputData", inputService.displayInput(id));
		log.info("this is the new input added 123:"+ inputService.displayInput(id));
		return "loginProfile";

	}

	/**
	 * Method to direct/redirect customers to profile page when clicked from login
	 * or add/view transaction tab
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("loginProfile")
	public String redirectToProfilePage(HttpServletRequest request, Model model) {
		log.info("Redirecting to profile page...");

		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);
		model.addAttribute("inputData", inputService.displayInput(id));
		model.addAttribute("helloMessage", username);
		return userService.authenticateUser(username) ? "loginProfile" : "error";
	}

	/**
	 * Method to direct/redirect customers to expenses tab where it will display the
	 * expenses pie chart and expenses&savings line graph
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("expenses")
	public String redirectToExpensesPage(HttpServletRequest request, Model model) {
		
		log.info("Redirecting to expenses page...");
		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);

		model.addAttribute("incomeGraph", retrivalService.displayCategory(InputCategory.income.toString(), id));
		model.addAttribute("expenseGraph", retrivalService.displayExpense(InputCategory.income.toString(), id));

		model.addAttribute("transportChart", retrivalService.displayCategory(InputCategory.transport.toString(), id));
		model.addAttribute("foodanddrinksChart",
				retrivalService.displayCategory(InputCategory.foodanddrinks.toString(), id));
		model.addAttribute("entertainmentChart",
				retrivalService.displayCategory(InputCategory.entertainment.toString(), id));
		model.addAttribute("billsChart", retrivalService.displayCategory(InputCategory.bills.toString(), id));
		model.addAttribute("insuranceChart", retrivalService.displayCategory(InputCategory.insurance.toString(), id));
		model.addAttribute("othersChart", retrivalService.displayCategory(InputCategory.others.toString(), id));

		return userService.authenticateUser(username) ? "expenses" : "error";
	}

	/**
	 * Method to direct/redirect customers to savings page where it will display the
	 * savings throughout the months
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("savings")
	public String redirectToSavingsPage(HttpServletRequest request, Model model) {

		log.info("Redirecting to savings page...");
		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);

		Map<String, Integer> month = new HashMap<>();
		month.put("jan", 1);
		month.put("feb", 2);
		month.put("mar", 3);
		month.put("apr", 4);
		month.put("may", 5);
		month.put("jun", 6);
		month.put("jul", 7);
		month.put("aug", 8);
		month.put("sep", 9);
		month.put("oct", 10);
		month.put("nov", 11);
		month.put("dec", 12);

		Map<String, Integer> year = new HashMap<>();
		year.put("21", 2021);
		year.put("22", 2022);

		model.addAttribute("may21", retrivalService.displaySavingsMonthly(month.get("may"), year.get("21"), id));
		model.addAttribute("jun21", retrivalService.displaySavingsMonthly(month.get("jun"), year.get("21"), id));
		model.addAttribute("jul21", retrivalService.displaySavingsMonthly(month.get("jul"), year.get("21"), id));
		model.addAttribute("aug21", retrivalService.displaySavingsMonthly(month.get("aug"), year.get("21"), id));
		model.addAttribute("sep21", retrivalService.displaySavingsMonthly(month.get("sep"), year.get("21"), id));
		model.addAttribute("oct21", retrivalService.displaySavingsMonthly(month.get("oct"), year.get("21"), id));
		model.addAttribute("nov21", retrivalService.displaySavingsMonthly(month.get("nov"), year.get("21"), id));
		model.addAttribute("dec21", retrivalService.displaySavingsMonthly(month.get("dec"), year.get("21"), id));

		model.addAttribute("jan22", retrivalService.displaySavingsMonthly(month.get("jan"), year.get("22"), id));
		model.addAttribute("feb22", retrivalService.displaySavingsMonthly(month.get("feb"), year.get("22"), id));
		model.addAttribute("mar22", retrivalService.displaySavingsMonthly(month.get("mar"), year.get("22"), id));
		model.addAttribute("apr22", retrivalService.displaySavingsMonthly(month.get("apr"), year.get("22"), id));

		return userService.authenticateUser(username) ? "savings" : "error";
	}

	/**
	 * Method to direct/redirect customers to the search tab
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping("searchInput")
	public String redirectingToSearchPage(HttpServletRequest request) {
		log.info("Redirecting to search page...");
		String username = (String) request.getSession().getAttribute("username");

		return userService.authenticateUser(username) ? "searchInput" : "error";
	}

	/**
	 * Method to retrive input based on search results in a table format
	 * 
	 * @param request
	 * @param model
	 * @param searchWord
	 * @return String
	 */
	@RequestMapping("searchInputForm")
	public String searchingInput(HttpServletRequest request, Model model, @RequestParam String searchWord) {
		log.info("Searching for input");
		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);

		log.info("This below are the results " + inputService.searchInput(searchWord, id));

		model.addAttribute("inputData", inputService.searchInput(searchWord, id));

		return "searchInput";
	}

	/**
	 * Method to delete input based on result clicked
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteInput{id}")
	public String deleteInput(@PathVariable(name = "id") int id) {
		log.info("Deleting input " + id);

		inputService.deleteInput(id);

		return "searchInput";
	}
	
	/**
	 * Method to delete input in loginProfile
	 * @param id
	 * @return StringdeleteRecentInput
	 */
	@RequestMapping("deleteRecentInput{id}")
	public String deleteRecentInput(@PathVariable(name = "id") int id) {
		log.info("Deleting recent input " + id);

		inputService.deleteInput(id);

		return "redirect:loginProfile";
	}

	/**
	 * Method to redirect/direct to compare expenses page
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("compareExpenses")
	public String redirectingToCompareExpensesPage(HttpServletRequest request, Model model) {
		log.info("Redirecting to compare expenses page...");
		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);

		model.addAttribute("transportChart", retrivalService.displayCategory(InputCategory.transport.toString(), id));
		model.addAttribute("foodanddrinksChart",
				retrivalService.displayCategory(InputCategory.foodanddrinks.toString(), id));
		model.addAttribute("entertainmentChart",
				retrivalService.displayCategory(InputCategory.entertainment.toString(), id));
		model.addAttribute("billsChart", retrivalService.displayCategory(InputCategory.bills.toString(), id));
		model.addAttribute("insuranceChart", retrivalService.displayCategory(InputCategory.insurance.toString(), id));
		model.addAttribute("othersChart", retrivalService.displayCategory(InputCategory.others.toString(), id));
		model.addAttribute("totalExpensesMonth", retrivalService.displayExpense(InputCategory.income.toString(), id));

		return userService.authenticateUser(username) ? "compareExpenses" : "error";
	}

	/**
	 * Method to take an input month and year from customer, and return expenses
	 * from the selection
	 * 
	 * @param request
	 * @param model
	 * @param searchMonth
	 * @return
	 */
	@RequestMapping("searchMonth")
	public String searchingMonth(HttpServletRequest request, Model model, @RequestParam String searchMonth) {
		log.info("Searching for month " + searchMonth);
		String username = (String) request.getSession().getAttribute("username");
		int id = retrivalService.retriveId(username);
		
		
		String[] exp = searchMonth.split("-");
		int month = Integer.parseInt(exp[1]);
		int year = Integer.parseInt(exp[0]);
		String monthString;
		if(month==1) {
			monthString = "Jan";
		}else if(month ==2) {
			monthString = "Feb";
		}else if(month ==3) {
			monthString = "Mar";
		}else if(month ==4) {
			monthString = "Apr";
		}else if(month ==5) {
			monthString = "May";
		}else if(month ==6) {
			monthString = "Jun";
		}else if(month ==7) {
			monthString = "Jul";
		}else if(month ==8) {
			monthString = "Aug";
		}else if(month ==9) {
			monthString = "Sep";
		}else if(month ==10) {
			monthString = "Oct";
		}else if(month ==11) {
			monthString = "Nov";
		}else {
			monthString = "Dec";
		}
		
		
		model.addAttribute("transportChart", retrivalService.displayCategory(InputCategory.transport.toString(), id));
		model.addAttribute("foodanddrinksChart",
				retrivalService.displayCategory(InputCategory.foodanddrinks.toString(), id));
		model.addAttribute("entertainmentChart",
				retrivalService.displayCategory(InputCategory.entertainment.toString(), id));
		model.addAttribute("billsChart", retrivalService.displayCategory(InputCategory.bills.toString(), id));
		model.addAttribute("insuranceChart", retrivalService.displayCategory(InputCategory.insurance.toString(), id));
		model.addAttribute("othersChart", retrivalService.displayCategory(InputCategory.others.toString(), id));
		model.addAttribute("totalExpensesMonth", retrivalService.displayExpense(InputCategory.income.toString(), id));

		model.addAttribute("monthBasedOnSearch", monthString + " " + year);
		model.addAttribute("transportChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.transport.toString(), id));
		model.addAttribute("foodanddrinksChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.foodanddrinks.toString(), id));
		model.addAttribute("entertainmentChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.entertainment.toString(), id));
		model.addAttribute("billsChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.bills.toString(), id));
		model.addAttribute("insuranceChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.insurance.toString(), id));
		model.addAttribute("othersChartByMonth",
				retrivalService.displayCategoryByMonth(month, year, InputCategory.others.toString(), id));
		model.addAttribute("totalExpensesByChoosenMonth",
				retrivalService.displayExpenseByMonth(month, year, InputCategory.income.toString(), id));

		return "compareExpenses";
	}

}

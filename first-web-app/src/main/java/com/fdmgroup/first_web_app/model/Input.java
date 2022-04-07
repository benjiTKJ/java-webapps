package com.fdmgroup.first_web_app.model;

import java.util.Date;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Input class with attributes and behaviours of the input
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Entity
public class Input {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String category;
	private String description;
	private double amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date localDate;
	@ManyToOne
	private Customer customer;

	/**
	 * No argument constructor
	 */
	public Input() {
	}

	/**
	 * Some argument constructor
	 * @param category
	 * @param description
	 * @param amount
	 * @param localDate
	 * @param customer
	 */
	public Input(String category, String description, double amount, Date localDate, Customer customer) {
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.localDate = localDate;
		this.customer = customer;
	}


	/**
	 * Getter for id
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for category 
	 * @return	String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter for category
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Getter for description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for amount
	 * @return double
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Setter for amount
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Getter for localDate
	 * @return Date
	 */
	public Date getLocalDate() {
		return localDate;
	}

	/**
	 * Setter for localDate
	 * @param localDate
	 */
	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	/**
	 * Getter for customer
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Setter for customer
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * To String method
	 */
	@Override
	public String toString() {
		return "Input [id=" + id + ", category=" + category + ", description=" + description + ", amount=" + amount
				+ ", localDate=" + localDate + ", customer=" + customer + "]";
	}

}

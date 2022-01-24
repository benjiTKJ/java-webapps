package com.fdmgroup.first_web_app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Customer class with attributes and behaviours of customer
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Component
@Entity
@Table
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Input> inputs;

	/**
	 * No argument constructor 
	 */
	public Customer() {
	}

	/**
	 * Some argument constructor
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 * @param inputs
	 */
	public Customer(String firstName, String lastName, String username, String password, String email,
			List<Input> inputs) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.inputs = inputs;
	}

	/**
	 * Some argument constructor
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param email
	 */
	public Customer(String firstName, String lastName, String username, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/**
	 * Getter for id
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for firstName
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for lastName
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for username
	 * @return	String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for email 
	 * @return	String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * To String method
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + "]";
	}

}

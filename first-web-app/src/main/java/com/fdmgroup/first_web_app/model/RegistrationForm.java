package com.fdmgroup.first_web_app.model;

import org.springframework.stereotype.Component;

/**
 * RegistrationForm class with attributes and behvaiours for registrationForm
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Component
public class RegistrationForm {

	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String comfirmPassword;
	private String email;

	/**
	 * No argument constructor
	 */
	public RegistrationForm() {
	}

	/**
	 * Some argument constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param comfirmPassword
	 * @param email
	 */
	public RegistrationForm(String firstname, String lastname, String username, String password, String comfirmPassword,
			String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.comfirmPassword = comfirmPassword;
		this.email = email;
	}

	/**
	 * Getter for firstName
	 * @return String
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Setter for firstName
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Getter for lastnName
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Setter for lastName
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Getter for username
	 * @return String
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
	 * @return	String
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
	 * Getter for comfirmPassword
	 * @return	String
	 */
	public String getComfirmPassword() {
		return comfirmPassword;
	}

	/**
	 * Setter for comfirmPassword
	 * @param comfirmPassword
	 */
	public void setComfirmPassword(String comfirmPassword) {
		this.comfirmPassword = comfirmPassword;
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
	 * To string method 
	 */
	@Override
	public String toString() {
		return "RegistrationForm [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", comfirmPassword=" + comfirmPassword + ", email=" + email + "]";
	}
	
	
	

	

}

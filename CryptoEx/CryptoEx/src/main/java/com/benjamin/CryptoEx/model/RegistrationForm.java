package com.benjamin.CryptoEx.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
public class RegistrationForm {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String comfirmPassword;
	private String email;
	private String number;
	public RegistrationForm(String firstName, String lastName, String username, String password, String comfirmPassword,
			String email, String number) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.comfirmPassword = comfirmPassword;
		this.email = email;
		this.number = number;
	}
	public RegistrationForm() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getComfirmPassword() {
		return comfirmPassword;
	}
	public void setComfirmPassword(String comfirmPassword) {
		this.comfirmPassword = comfirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "RegistrationForm [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", comfirmPassword=" + comfirmPassword + ", email=" + email + ", number="
				+ number + "]";
	}
	
	
	
}

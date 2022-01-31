package com.benjamin.CryptoEx.model;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table
public class CustomerDetail {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_detail_id")
	private List<CryptoPortfolio> cryptoPortfolio;
	
	public CustomerDetail(String firstName, String lastName, String username, String password, String email,
			String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public CustomerDetail(String firstName, String lastName, String username, String password, String email,
			String phoneNumber, List<CryptoPortfolio> cryptoPortfolio) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cryptoPortfolio = cryptoPortfolio;
	}

	public CustomerDetail() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<CryptoPortfolio> getCryptoPortfolio() {
		return cryptoPortfolio;
	}

	public void setCryptoPortfolio(List<CryptoPortfolio> cryptoPortfolio) {
		this.cryptoPortfolio = cryptoPortfolio;
	}

	@Override
	public String toString() {
		return "CustomerDetail [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", cryptoPortfolio=" + cryptoPortfolio + "]";
	}
	
	
	
	
}

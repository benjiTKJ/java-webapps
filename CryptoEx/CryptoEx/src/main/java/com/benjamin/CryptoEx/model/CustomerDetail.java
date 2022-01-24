package com.benjamin.CryptoEx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
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
	
	public CustomerDetail(String firstName, String lastName, String username, String password, String email,
			String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	
	
}

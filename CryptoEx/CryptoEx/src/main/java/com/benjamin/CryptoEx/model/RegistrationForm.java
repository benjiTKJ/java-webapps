package com.benjamin.CryptoEx.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationForm {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String comfirmPassword;
	private String email;
	private String number;
	
}

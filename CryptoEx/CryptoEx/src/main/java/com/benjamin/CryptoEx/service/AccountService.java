package com.benjamin.CryptoEx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benjamin.CryptoEx.model.CryptoPortfolio;
import com.benjamin.CryptoEx.repository.IUserRepository;

@Service
public class AccountService {

	@Autowired
	IUserRepository userRepository;

	public int retriveId(String username) {
		System.out.println("Id:" +userRepository.getIdOfCustomer(username)+" of " +username+" found");
		return userRepository.getIdOfCustomer(username);
	}

	
	
}

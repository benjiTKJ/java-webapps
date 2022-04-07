package com.benjamin.CryptoEx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benjamin.CryptoEx.model.CryptoCoinList;
import com.benjamin.CryptoEx.model.CryptoPortfolio;
import com.benjamin.CryptoEx.model.CustomerDetail;
import com.benjamin.CryptoEx.repository.ICryptoCoinRepository;
import com.benjamin.CryptoEx.repository.ICryptoPortfolioRepository;
import com.benjamin.CryptoEx.repository.IUserRepository;

@Service
public class CryptoCoinService {

	@Autowired
	ICryptoCoinRepository cryptoCoinRepository;
	
	@Autowired
	ICryptoPortfolioRepository cryptoPortfolioRepository;
	
	@Autowired
	IUserRepository userRepository;
	public boolean verifyIfCoinExist(String name, String symbol) {
		if(cryptoCoinRepository.findByCoinName(name).isPresent() || cryptoCoinRepository.findByCoinSymbol(symbol).isPresent()) {
			return true;
		}
		return false;
	}

	public void addNewCryptoCoinName(String capitalizedName, String symbol) {
		cryptoCoinRepository.save(new CryptoCoinList(capitalizedName,symbol));
	}

	public List<String> findAllCryptoCoinName() {
		
		return cryptoCoinRepository.findAllCryptoCoinName();
	}

	public int retriveIdOfCoin(String name) {
		
		return cryptoCoinRepository.getIdByCoinName(name);
	}

	public void addNewBuyTransaction(CryptoPortfolio cryptoPortfolio, int customerId, int coinId) {
		
		if(userRepository.findById(customerId)!=null) {
			Optional<CustomerDetail> foundCustomerDetail = userRepository.findById(customerId);
			if(foundCustomerDetail.isPresent()) {
				CustomerDetail customer = foundCustomerDetail.get();
				cryptoPortfolio.setCustomerDetail(customer);
				Optional<CryptoCoinList> foundCoinName = cryptoCoinRepository.findById(coinId);
				CryptoCoinList coinName = foundCoinName.get();
				cryptoPortfolio.setCryptoCoinList(coinName);
				
				
				
				cryptoPortfolioRepository.save(cryptoPortfolio);
			}
		}
		
		
		
		
	}

	public List<CryptoPortfolio> getPortfolioFromCustomerId(int customerId) {
	
		return cryptoPortfolioRepository.getPortfolioFromCustomerId(customerId);
	}

	public boolean checkIfCoinNameExistInPortfolio(int customerId, int coinId) {
		if(cryptoPortfolioRepository.findByCustomerIdAndCoinId(customerId,coinId)!=null) {
			return true;
		}
		return false;
	}

	public void addBuyTransactionAveragePrice(CryptoPortfolio cryptoPortfolio, int customerId, int coinId) {
		if(userRepository.findById(customerId)!=null) {
			Optional<CustomerDetail> foundCustomerDetail = userRepository.findById(customerId);
			if(foundCustomerDetail.isPresent()) {
				CustomerDetail customer = foundCustomerDetail.get();
				cryptoPortfolio.setCustomerDetail(customer);
				Optional<CryptoCoinList> foundCoinName = cryptoCoinRepository.findById(coinId);
				CryptoCoinList coinName = foundCoinName.get();
				cryptoPortfolio.setCryptoCoinList(coinName);
				
				double currentQuantity = cryptoPortfolioRepository.getQuantityByCoinId(coinId,customerId);
				System.out.println("Current Quantity is: "+currentQuantity);
				double currentPrice = cryptoPortfolioRepository.getPriceByCoinId(coinId,customerId);
				System.out.println("Current Price is: "+currentPrice);
				double newQuantity = cryptoPortfolio.getQuantity();
				double newQuantityAvg = currentQuantity + newQuantity;
				System.out.println("New average quantity :"+newQuantityAvg);
				double newPrice = cryptoPortfolio.getPrice();
				double newPriceAvg = ((currentQuantity*currentPrice) + (newQuantity*newPrice))/newQuantityAvg;
				int currentPorfolioId = cryptoPortfolioRepository.getIdByCoinId(coinId,customerId);
				cryptoPortfolioRepository.deleteById(currentPorfolioId);
				
				cryptoPortfolioRepository.save(new CryptoPortfolio(cryptoPortfolio.getName(), newPriceAvg, newQuantityAvg, cryptoPortfolio.getLocalDate(), customer, coinName));
				
			}
		}
		
	}

	

}

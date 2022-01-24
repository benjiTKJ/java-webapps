package com.benjamin.CryptoEx.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.benjamin.CryptoEx.api.CoinGeckoApiClient;
import com.benjamin.CryptoEx.api.impl.CoinGeckoApiClientImpl;
import com.benjamin.CryptoEx.api.constant.Currency;

@Controller
public class CryptoListingController {

	CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
	 
	public List<String> coinsList(){
		List<String> coins = new ArrayList();
		coins.add("bitcoin");
		coins.add("ethereum");
		coins.add("ripple");
		
		return coins;
	}
	
	
	public Double getCoinPrice(Map<String, Map<String, Double>> coinMap) {
		String coin = coinMap.values().toString();
		coin = coin.replaceAll("[^0-9.]", "");
		return Double.parseDouble(coin);
	}
	
	public Double getCoinChangeInPrice(Map<String, Map<String, Double>> coinMap) {
		String coin = coinMap.values().toString();
		coin = (String) coin.subSequence(26, coin.length());
		coin = coin.replaceAll("[^0-9.-]", "");
		double coinChange = Double.parseDouble(coin);
		coinChange = Math.round(coinChange*10000d)/10000d;
		return coinChange;
	}
	
	@RequestMapping("cryptoListing")
	public String goToCrytoListing(Model model) {
		System.out.println("Going to Crypto Listing (Not Logged in)");
		
		Map<String, Map<String, Double>> bitcoinPrice = client.getPrice("bitcoin",Currency.USD);	
		model.addAttribute("bitcoinPrice", getCoinPrice(bitcoinPrice));
		Map<String, Map<String, Double>> bitcoinChange = client.getPrice("bitcoin", Currency.USD, false, false, true, false);
		model.addAttribute("bitcoinChange", getCoinChangeInPrice(bitcoinChange));
		
		
		
		
		return "cryptoListing";
	}
	
	
	
}

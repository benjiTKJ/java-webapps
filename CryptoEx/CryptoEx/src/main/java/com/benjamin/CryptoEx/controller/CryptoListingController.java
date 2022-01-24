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
	
	
	public double getCoinPrice(String coin) {
		return 1.1;
	}
	
	@RequestMapping("cryptoListing")
	public String goToCrytoListing(Model model) {
		System.out.println("Going to Crypto Listing (Not Logged in)");
		
		Map<String, Map<String, Double>> bitcoin = client.getPrice("ripple",Currency.USD);
		String bitcoinPrice = bitcoin.values().toString();
		bitcoinPrice = bitcoinPrice.replaceAll("[^0-9.]", " ");
		
		
		model.addAttribute("bitcoin", bitcoinPrice);
		
		model.addAttribute("testing", client.getPrice("bitcoin", Currency.USD, false, false, true, false));
		
		model.addAttribute("coinsData", coinsList());
		
		
		return "cryptoListing";
	}
	
	
	
}

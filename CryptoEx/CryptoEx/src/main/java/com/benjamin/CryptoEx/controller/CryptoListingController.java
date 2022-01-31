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
import org.springframework.web.bind.annotation.RequestParam;

import com.benjamin.CryptoEx.api.CoinGeckoApiClient;
import com.benjamin.CryptoEx.api.impl.CoinGeckoApiClientImpl;
import com.benjamin.CryptoEx.api.constant.Currency;

@Controller
public class CryptoListingController {

	CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

	public List<String> coinsList() {
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
		coinChange = Math.round(coinChange * 10000d) / 10000d;
		return coinChange;
	}

	@RequestMapping("cryptoListing")
	public String goToCrytoListing(Model model, String currency) {
		System.out.println("Going to Crypto Listing (Not Logged in)");

		Map<String, Map<String, Double>> bitcoinPrice = client.getPrice("bitcoin", Currency.USD);
		model.addAttribute("bitcoinPrice", getCoinPrice(bitcoinPrice));
		Map<String, Map<String, Double>> bitcoinChange = client.getPrice("bitcoin", Currency.USD, false, false, true,
				false);
		model.addAttribute("bitcoinChange", getCoinChangeInPrice(bitcoinChange));

		/*
		 * // Calculation of SGD Map<String, Map<String, Double>> bitcoinPriceSGD =
		 * client.getPrice("bitcoin", Currency.SGD); double sgd =
		 * getCoinPrice(bitcoinPriceSGD) / getCoinPrice(bitcoinPrice);
		 * 
		 * model.addAttribute("sgdRate", Math.round(sgd*10000d)/10000d); //
		 * 
		 * Map<String, Map<String, Double>> ethereumPrice = client.getPrice("ethereum",
		 * Currency.USD); model.addAttribute("ethereumPrice",
		 * getCoinPrice(ethereumPrice)); Map<String, Map<String, Double>> ethereumChange
		 * = client.getPrice("ethereum", Currency.USD, false, false, true, false);
		 * model.addAttribute("ethereumChange", getCoinChangeInPrice(ethereumChange));
		 * 
		 * Map<String, Map<String, Double>> tetherPrice = client.getPrice("tether",
		 * Currency.USD); model.addAttribute("tetherPrice", getCoinPrice(tetherPrice));
		 * Map<String, Map<String, Double>> tetherChange = client.getPrice("tether",
		 * Currency.USD, false, false, true, false); model.addAttribute("tetherChange",
		 * getCoinChangeInPrice(tetherChange));
		 * 
		 * Map<String, Map<String, Double>> binanceCoinPrice =
		 * client.getPrice("binancecoin", Currency.USD);
		 * model.addAttribute("binanceCoinPrice", getCoinPrice(binanceCoinPrice));
		 * Map<String, Map<String, Double>> binanceCoinChange =
		 * client.getPrice("binancecoin", Currency.USD, false, false, true, false);
		 * model.addAttribute("binanceCoinChange",
		 * getCoinChangeInPrice(binanceCoinChange));
		 * 
		 * Map<String, Map<String, Double>> usdCoinPrice = client.getPrice("usd-coin",
		 * Currency.USD); model.addAttribute("usdCoinPrice",
		 * getCoinPrice(usdCoinPrice)); Map<String, Map<String, Double>> usdCoinChange =
		 * client.getPrice("usd-coin", Currency.USD, false, false, true, false);
		 * model.addAttribute("usdCoinChange", getCoinChangeInPrice(usdCoinChange));
		 * 
		 * Map<String, Map<String, Double>> cardanoPrice = client.getPrice("cardano",
		 * Currency.USD); model.addAttribute("cardanoPrice",
		 * getCoinPrice(cardanoPrice)); Map<String, Map<String, Double>> cardanoChange =
		 * client.getPrice("cardano", Currency.USD, false, false, true, false);
		 * model.addAttribute("cardanoChange", getCoinChangeInPrice(cardanoChange));
		 * 
		 * Map<String, Map<String, Double>> ripplePrice = client.getPrice("ripple",
		 * Currency.USD); model.addAttribute("ripplePrice", getCoinPrice(ripplePrice));
		 * Map<String, Map<String, Double>> rippleChange = client.getPrice("ripple",
		 * Currency.USD, false, false, true, false); model.addAttribute("rippleChange",
		 * getCoinChangeInPrice(rippleChange));
		 * 
		 * Map<String, Map<String, Double>> solanaPrice = client.getPrice("solana",
		 * Currency.USD); model.addAttribute("solanaPrice", getCoinPrice(solanaPrice));
		 * Map<String, Map<String, Double>> solanaChange = client.getPrice("solana",
		 * Currency.USD, false, false, true, false); model.addAttribute("solanaChange",
		 * getCoinChangeInPrice(solanaChange));
		 * 
		 * Map<String, Map<String, Double>> terraPrice = client.getPrice("terra-luna",
		 * Currency.USD); model.addAttribute("terraPrice", getCoinPrice(terraPrice));
		 * Map<String, Map<String, Double>> terraChange = client.getPrice("terra-luna",
		 * Currency.USD, false, false, true, false); model.addAttribute("terraChange",
		 * getCoinChangeInPrice(terraChange));
		 * 
		 * Map<String, Map<String, Double>> polkadotPrice = client.getPrice("polkadot",
		 * Currency.USD); model.addAttribute("polkadotPrice",
		 * getCoinPrice(polkadotPrice)); Map<String, Map<String, Double>> polkadotChange
		 * = client.getPrice("polkadot", Currency.USD, false, false, true, false);
		 * model.addAttribute("polkadotChange", getCoinChangeInPrice(polkadotChange));
		 */

		return "cryptoListing";
	}

	@RequestMapping("selectCurrency")
	public String selectCurrency(@RequestParam String currency) {

		return currency;
	}

}

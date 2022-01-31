 package com.benjamin.CryptoEx.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.benjamin.CryptoEx.api.CoinGeckoApiClient;
import com.benjamin.CryptoEx.api.constant.Currency;
import com.benjamin.CryptoEx.api.impl.CoinGeckoApiClientImpl;
import com.benjamin.CryptoEx.model.CryptoCoinList;
import com.benjamin.CryptoEx.model.CryptoPortfolio;
import com.benjamin.CryptoEx.service.AccountService;
import com.benjamin.CryptoEx.service.CryptoCoinService;
import com.benjamin.CryptoEx.service.UserService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CryptoCoinService cryptoCoinService;
	
	@Autowired
	CryptoListingController cryptoListingController;
	
	CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
	
	public String getUsernameOfCustomer(HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		return username;
	}
	
	public int getIdOfCustomer(HttpServletRequest request) {
		String username = getUsernameOfCustomer(request);
		int id = accountService.retriveId(username);
		return id;
	}
	
	@RequestMapping("customerAccount")
	public String goToCustomerAccount(HttpServletRequest request, Model model) {
		String username = getUsernameOfCustomer(request);
		model.addAttribute("helloMessage", username);
		
		List<String> cryptoName = cryptoCoinService.findAllCryptoCoinName();
		model.addAttribute("cryptoName", cryptoName);
		
		int customerId = userService.retriveId(username);
		List<CryptoPortfolio> cryptoPortfolioOfCustomer = cryptoCoinService.getPortfolioFromCustomerId(customerId);
		cryptoPortfolioOfCustomer.forEach(System.out::println);
		
		model.addAttribute("cryptoPortfolio", cryptoPortfolioOfCustomer);
		
		return userService.authenticateUser(username) ?"customerAccount":"error";
	}
	
	@RequestMapping("loginIndex")
	public String goToLoginIndex(HttpServletRequest request) {
		String username = getUsernameOfCustomer(request);
		return userService.authenticateUser(username)?"loginIndex":"error";
	}
	
	@RequestMapping("loginCryptoListing")
	public String goToLoginCryptoListing(HttpServletRequest request, Model model) {
		String username = getUsernameOfCustomer(request);
		
		System.out.println("Going to Crypto Listing (Not Logged in)");

		Map<String, Map<String, Double>> bitcoinPrice = client.getPrice("bitcoin", Currency.USD);
		model.addAttribute("bitcoinPrice", 	cryptoListingController.getCoinPrice(bitcoinPrice));
		Map<String, Map<String, Double>> bitcoinChange = client.getPrice("bitcoin", Currency.USD, false, false, true,
				false);
		model.addAttribute("bitcoinChange", cryptoListingController.getCoinChangeInPrice(bitcoinChange));

		// Calculation of SGD
		Map<String, Map<String, Double>> bitcoinPriceSGD = client.getPrice("bitcoin", Currency.SGD);
		double sgd = cryptoListingController.getCoinPrice(bitcoinPriceSGD) / cryptoListingController.getCoinPrice(bitcoinPrice);
		
		model.addAttribute("sgdRate", Math.round(sgd*10000d)/10000d);
		//

		Map<String, Map<String, Double>> ethereumPrice = client.getPrice("ethereum", Currency.USD);
		model.addAttribute("ethereumPrice", cryptoListingController.getCoinPrice(ethereumPrice));
		Map<String, Map<String, Double>> ethereumChange = client.getPrice("ethereum", Currency.USD, false, false, true,
				false);
		model.addAttribute("ethereumChange", cryptoListingController.getCoinChangeInPrice(ethereumChange));

		Map<String, Map<String, Double>> tetherPrice = client.getPrice("tether", Currency.USD);
		model.addAttribute("tetherPrice", cryptoListingController.getCoinPrice(tetherPrice));
		Map<String, Map<String, Double>> tetherChange = client.getPrice("tether", Currency.USD, false, false, true,
				false);
		model.addAttribute("tetherChange", cryptoListingController.getCoinChangeInPrice(tetherChange));

		Map<String, Map<String, Double>> binanceCoinPrice = client.getPrice("binancecoin", Currency.USD);
		model.addAttribute("binanceCoinPrice", cryptoListingController.getCoinPrice(binanceCoinPrice));
		Map<String, Map<String, Double>> binanceCoinChange = client.getPrice("binancecoin", Currency.USD, false, false,
				true, false);
		model.addAttribute("binanceCoinChange", cryptoListingController.getCoinChangeInPrice(binanceCoinChange));

		Map<String, Map<String, Double>> usdCoinPrice = client.getPrice("usd-coin", Currency.USD);
		model.addAttribute("usdCoinPrice", cryptoListingController.getCoinPrice(usdCoinPrice));
		Map<String, Map<String, Double>> usdCoinChange = client.getPrice("usd-coin", Currency.USD, false, false, true,
				false);
		model.addAttribute("usdCoinChange", cryptoListingController.getCoinChangeInPrice(usdCoinChange));

		Map<String, Map<String, Double>> cardanoPrice = client.getPrice("cardano", Currency.USD);
		model.addAttribute("cardanoPrice", cryptoListingController.getCoinPrice(cardanoPrice));
		Map<String, Map<String, Double>> cardanoChange = client.getPrice("cardano", Currency.USD, false, false, true,
				false);
		model.addAttribute("cardanoChange", cryptoListingController.getCoinChangeInPrice(cardanoChange));

		Map<String, Map<String, Double>> ripplePrice = client.getPrice("ripple", Currency.USD);
		model.addAttribute("ripplePrice", cryptoListingController.getCoinPrice(ripplePrice));
		Map<String, Map<String, Double>> rippleChange = client.getPrice("ripple", Currency.USD, false, false, true,
				false);
		model.addAttribute("rippleChange", cryptoListingController.getCoinChangeInPrice(rippleChange));

		Map<String, Map<String, Double>> solanaPrice = client.getPrice("solana", Currency.USD);
		model.addAttribute("solanaPrice", cryptoListingController.getCoinPrice(solanaPrice));
		Map<String, Map<String, Double>> solanaChange = client.getPrice("solana", Currency.USD, false, false, true,
				false);
		model.addAttribute("solanaChange", cryptoListingController.getCoinChangeInPrice(solanaChange));

		Map<String, Map<String, Double>> terraPrice = client.getPrice("terra-luna", Currency.USD);
		model.addAttribute("terraPrice", cryptoListingController.getCoinPrice(terraPrice));
		Map<String, Map<String, Double>> terraChange = client.getPrice("terra-luna", Currency.USD, false, false, true,
				false);
		model.addAttribute("terraChange", cryptoListingController.getCoinChangeInPrice(terraChange));

		Map<String, Map<String, Double>> polkadotPrice = client.getPrice("polkadot", Currency.USD);
		model.addAttribute("polkadotPrice", cryptoListingController.getCoinPrice(polkadotPrice));
		Map<String, Map<String, Double>> polkadotChange = client.getPrice("polkadot", Currency.USD, false, false, true,
				false);
		model.addAttribute("polkadotChange", cryptoListingController.getCoinChangeInPrice(polkadotChange));
		
		
		return userService.authenticateUser(username)?"loginCryptoListing":"error";
	}
	
	//errors in method causes stackOverflowError
	@PostMapping("addNewBuyTransaction")
	public String addNewBuyTransaction(CryptoPortfolio cryptoPortfolio, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) throws ParseException {
		String username = getUsernameOfCustomer(request);
		int customerId = userService.retriveId(username);
		int coinId = cryptoCoinService.retriveIdOfCoin(cryptoPortfolio.getName());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat.format(cryptoPortfolio.getLocalDate()));
		cryptoPortfolio.setLocalDate(newDate);
		
		
		  cryptoCoinService.addNewBuyTransaction(cryptoPortfolio,customerId,coinId);
		  System.out.println("Added new Buy Transaction by :"+username);
		  
		  redirectAttributes.addFlashAttribute("successMessage","Buy transaction added!");
		 
		return "redirect:customerAccount";
	}
	
	@PostMapping("addNewCoinName")
	public String addNewCoinName(CryptoCoinList cryptoCoinList, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String username = getUsernameOfCustomer(request);
		
		String name = cryptoCoinList.getName().toLowerCase();
		String firstLetter = name.substring(0, 1);
		String remainingLetter = name.substring(1);
		firstLetter = firstLetter.toUpperCase();
		String capitalizedName = firstLetter+remainingLetter;
		
		String symbol = cryptoCoinList.getSymbol().toUpperCase();
		
		model.addAttribute("errorMessage", null);
		if(cryptoCoinService.verifyIfCoinExist(capitalizedName,symbol)) {
			System.out.println("Coin "+ cryptoCoinList.getName() +" already exist, error in adding");
			redirectAttributes.addFlashAttribute("errorMessage", "Coin already exist!");
			return "redirect:customerAccount";
		}else {
			cryptoCoinService.addNewCryptoCoinName(capitalizedName,symbol);
			System.out.println("Coin "+ cryptoCoinList.getName() +" added by user: "+ username);
			redirectAttributes.addFlashAttribute("successMessage", "Coin added");
			return "redirect:customerAccount";
		}
		
		
	}
	
}

package com.benjamin.CryptoEx.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class CryptoCoinList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String symbol;
	@OneToMany
	@JoinColumn(name="crypto_coin_list_id")
	private  List<CryptoPortfolio> cryptoPortfolio;
	
	public CryptoCoinList(String name, String symbol) {
		super();
		this.name = name;
		this.symbol = symbol;
	}

	public CryptoCoinList() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<CryptoPortfolio> getCryptoPortfolio() {
		return cryptoPortfolio;
	}

	public void setCryptoPortfolio(List<CryptoPortfolio> cryptoPortfolio) {
		this.cryptoPortfolio = cryptoPortfolio;
	}

	@Override
	public String toString() {
		return "CryptoCoinList [id=" + id + ", name=" + name + ", symbol=" + symbol + ", cryptoPortfolio="
				+ cryptoPortfolio + "]";
	}
	
	
}

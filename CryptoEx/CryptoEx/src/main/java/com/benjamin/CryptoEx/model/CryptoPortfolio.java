package com.benjamin.CryptoEx.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class CryptoPortfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private double price;
	private double quantity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date localDate;
	@ManyToOne
	private CustomerDetail customerDetail;
	@ManyToOne
	private CryptoCoinList cryptoCoinList;
	
	
	public CryptoPortfolio(String name, double price, double quantity, Date localDate, CustomerDetail customerDetail,
			CryptoCoinList cryptoCoinList) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.localDate = localDate;
		this.customerDetail = customerDetail;
		this.cryptoCoinList = cryptoCoinList;
	}


	public CryptoPortfolio() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	public Date getLocalDate() {
		return localDate;
	}


	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}


	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}


	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}


	public CryptoCoinList getCryptoCoinList() {
		return cryptoCoinList;
	}


	public void setCryptoCoinList(CryptoCoinList cryptoCoinList) {
		this.cryptoCoinList = cryptoCoinList;
	}


	@Override
	public String toString() {
		return "CryptoPortfolio [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", localDate=" + localDate + "]";
	}


	
	
	
	
	
}

package com.benjamin.CryptoEx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benjamin.CryptoEx.model.CryptoPortfolio;

@Repository
public interface ICryptoPortfolioRepository extends JpaRepository<CryptoPortfolio, Integer> {

	@Query("SELECT c FROM CryptoPortfolio c, CustomerDetail d WHERE c.customerDetail = d.id AND d.id=:customerId")
	List<CryptoPortfolio> getPortfolioFromCustomerId(@Param("customerId")int customerId);

	
}

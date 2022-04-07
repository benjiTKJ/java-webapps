package com.benjamin.CryptoEx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benjamin.CryptoEx.model.CryptoPortfolio;

@Repository
public interface ICryptoPortfolioRepository extends JpaRepository<CryptoPortfolio, Integer> {

	@Query("SELECT c FROM CryptoPortfolio c, CustomerDetail d WHERE c.customerDetail = d.id AND d.id=:customerId")
	List<CryptoPortfolio> getPortfolioFromCustomerId(@Param("customerId")int customerId);

	@Query("SELECT c FROM CryptoPortfolio c WHERE c.customerDetail=:customerId AND c.cryptoCoinList=:coinId")
	Optional<CryptoPortfolio> findByCustomerIdAndCoinId(@Param("customerId")int customerId, @Param("coinId") int coinId);

	@Query("SELECT c.quantity FROM CryptoPortfolio c WHERE c.customerDetail=:customerId AND c.cryptoCoinList=:coinId")
	double getQuantityByCoinId(@Param("coinId")int coinId, @Param("customerId")int customerId);

	@Query("SELECT c.price FROM CryptoPortfolio c WHERE c.customerDetail=:customerId AND c.cryptoCoinList=:coinId")
	double getPriceByCoinId(@Param("coinId")int coinId, @Param("customerId")int customerId);

	@Query("SELECT c.id FROM CryptoPortfolio c WHERE c.customerDetail=:customerId AND c.cryptoCoinList=:coinId")
	int getIdByCoinId(@Param("coinId")int coinId, @Param("customerId")int customerId);

	

	

	
}

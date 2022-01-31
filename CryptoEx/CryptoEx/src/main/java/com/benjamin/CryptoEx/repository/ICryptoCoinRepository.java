package com.benjamin.CryptoEx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benjamin.CryptoEx.model.CryptoCoinList;

@Repository
public interface ICryptoCoinRepository extends JpaRepository<CryptoCoinList, Integer> {

	@Query("SELECT c FROM CryptoCoinList c WHERE c.name =:name")
	Optional<CryptoCoinList> findByCoinName(@Param("name")String name);
	
	@Query("SELECT c FROM CryptoCoinList c WHERE c.symbol =:symbol")
	Optional<CryptoCoinList> findByCoinSymbol(@Param("symbol")String symbol);

	@Query("SELECT c.name from CryptoCoinList c")
	List<String> findAllCryptoCoinName();

	@Query("SELECT c.id from CryptoCoinList c WHERE c.name =:name")
	int getIdByCoinName(@Param("name")String name);

}

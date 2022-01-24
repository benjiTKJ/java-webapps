package com.benjamin.CryptoEx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.benjamin.CryptoEx.model.CustomerDetail;

public interface IUserRepository extends JpaRepository<CustomerDetail, Integer> {

	Optional<CustomerDetail> findByUsername(String username);

	@Query("SELECT c.id from CustomerDetail c WHERE c.username = :username")
	int getIdOfCustomer(@Param("username")String username);

}

package com.fdmgroup.first_web_app.repository;


import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.first_web_app.model.Input;
/**
 * Input repository interface to query and return input 
 * @author Benjamin Tan
 * @version 0.0.1-SNAPSHOT 
 */
@Repository
public interface IInputRepository extends JpaRepository<Input, Integer>{

	/**
	 * Method to find all input by customer id
	 * @param id
	 * @return List<Input>
	 */
	@Query("SELECT i FROM Input i, Customer c WHERE i.customer = c.id AND c.id =:id "+
			" AND MONTH(i.localDate) = MONTH(CURRENT_DATE) ORDER BY i.localDate DESC")
	List<Input> findAllInputById(@Param("id") int id); 
	

	/**
	 * Method to find expenses of the current month by customer id
	 * @param income
	 * @param id
	 * @return double
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE MONTH(i.localDate) = MONTH(CURRENT_DATE) "+
			" AND i.category NOT LIKE :income AND i.customer = c.id AND c.id =:id" )
	double findByExpense(@Param("income") String income ,@Param("id") int id);

	/**
	 * Method with optional return value to find expenses of the current month by customer id  
	 * @param income
	 * @param id
	 * @return Optional<Double>
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE MONTH(i.localDate) = MONTH(CURRENT_DATE) "+
			" AND i.category NOT LIKE :income AND i.customer = c.id AND c.id =:id" )
	Optional<Double> findByExpenseOptional(String income, int id);


	/**
	 * Method to find income of the current month by customer id
	 * @param category
	 * @param id
	 * @return double
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE MONTH(i.localDate) = MONTH(CURRENT_DATE) "+
			" AND i.category = :category AND i.customer = c.id AND c.id =:id" )
	double findByCategory(@Param("category")String category,@Param("id") int id);

	/**
	 * Method with optional return value to find income of the current month by customer id
	 * @param category
	 * @param id
	 * @return Optional<Double>
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE MONTH(i.localDate) = MONTH(CURRENT_DATE) "+
			" AND i.category = :category AND i.customer = c.id AND c.id =:id" )
	Optional<Double> findByCategoryOptional(String category, int id);


	/**
	 * Method with optional return value to find income of a specific month by customer id
	 * @param month
	 * @param year
	 * @param category
	 * @param id
	 * @return Optional<Double>
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE YEAR(i.localDate) = :year AND MONTH(i.localDate) = :month "+
			" AND i.category = :category AND i.customer = c.id AND c.id =:id" )
	Optional<Double> findByCategoryMonthOptional(int month, int year, String category, int id);

	/**
	 * Method to find income of a specific month by customer id
	 * @param month
	 * @param year
	 * @param category
	 * @param id
	 * @return double
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE YEAR(i.localDate) = :year AND MONTH(i.localDate) = :month "+
			" AND i.category = :category AND i.customer = c.id AND c.id =:id" )
	double findByCategoryMonth(@Param("month")int month,@Param("year")int year, @Param("category")String category,@Param("id") int id);

	/**
	 * Method with optional return value to find expenses of a specific month by customer id
	 * @param month
	 * @param year
	 * @param income
	 * @param id
	 * @return Optional<Double>
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE YEAR(i.localDate) = :year AND MONTH(i.localDate) = :month "+
			" AND i.category NOT LIKE :income AND i.customer = c.id AND c.id =:id" )
	Optional<Double> findByExpenseMonthOptional(int month, int year, String income, int id);

	/**
	 * Method to find expenses of a specific month by customer id
	 * @param month
	 * @param year
	 * @param income
	 * @param id
	 * @return double
	 */
	@Query("SELECT SUM(i.amount) FROM Input i,"+
			" Customer c WHERE YEAR(i.localDate) = :year AND MONTH(i.localDate) = :month "+
			" AND i.category NOT LIKE :income AND i.customer = c.id AND c.id =:id" )
	double findByExpenseMonth(@Param("month") int month, @Param("year") int year,@Param("income") String income ,@Param("id") int id);

	/**
	 * Method to return input based on searchWord
	 * @param searchWord
	 * @param id
	 * @return	List<Input>
	 */
	@Query("SELECT DISTINCT i FROM Input i, Customer c WHERE i.customer = c.id AND c.id =:id "+
			" AND i.category LIKE %:searchWord% OR i.description LIKE %:searchWord% "+
			" OR i.localDate LIKE %:searchWord%	ORDER BY i.localDate DESC")
	List<Input> findAllInputBySearchWord(@Param("searchWord")String searchWord, @Param("id") int id);






	
	




	



	

	

	
	
	

	
	
	
	
	
	
}

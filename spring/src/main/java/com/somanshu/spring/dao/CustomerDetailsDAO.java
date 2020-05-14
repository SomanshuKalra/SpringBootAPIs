/**
 * 
 */
package com.somanshu.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.somanshu.spring.model.Customer;

/**
 * Description: DAO Interface for Spring Data JPA queries. Defines queries for:
 * 					1. Fetching customer information by first name.
 * 					2. Fetching customer information by region.
 * 					3. Fetching customer information by job classification.
 * 					4. Saving customer information to database.
 * 				  
 * @author Somanshu Kalra
 * Date:   13/05/2020
 *
 */
@Repository
public interface CustomerDetailsDAO extends CrudRepository<Customer, Integer>{

	//get details by first name
	List<Customer> findByCustomerFirstName(String name);

	//Save details to database
	Customer save(Customer customer);

	//Get details by region
	List<Customer> findByRegion(String region);
	
	//Get details by job classification
	List<Customer> findByJobClassification(String jobClassification);

}

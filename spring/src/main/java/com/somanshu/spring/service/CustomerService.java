/**
 * 
 */
package com.somanshu.spring.service;

import java.util.List;

import com.somanshu.spring.model.Customer;

/**
 * Description: Interface to define CustomerService class methods.
 * @author 		Somanshu Kalra
 * Date: 		13/05/2020
 * 
 */
public interface CustomerService {
	
	List<Customer> findByName(String name);
	
	Customer save(Customer customer);
	
	List<Customer> findByRegion(String region);
	
	List<Customer> findByJobClassification(String jobClassification);

}

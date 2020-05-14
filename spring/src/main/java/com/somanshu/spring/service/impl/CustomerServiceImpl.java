/**
 * 
 */
package com.somanshu.spring.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somanshu.spring.dao.CustomerDetailsDAO;
import com.somanshu.spring.model.Customer;
import com.somanshu.spring.service.CustomerService;

/**
 * Description: Service class for performing operations on Customer model. Contains 
 * 				business logic for search and save queries. Uses object of DAO to interact 
 * 				with database.
 *  
 * @author 		Somanshu Kalra
 * Date:		13/05/2020
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDetailsDAO customerDetailsDao;
	
	@Override
	public List<Customer> findByName(String name) {
		List<Customer> customer = customerDetailsDao.findByCustomerFirstName(name);
		return customer;
	}

	@Override
	public Customer save(Customer customer) {
		Customer addedCustomer = customerDetailsDao.save(customer);
		return addedCustomer;
	}

	@Override
	public List<Customer> findByRegion(String region) {
		List<Customer> allCustomers = customerDetailsDao.findByRegion(region);
		Iterator<Customer> listIterator = allCustomers.iterator();
		while(listIterator.hasNext()) {
			Customer customer = listIterator.next();
			customer.setRegion(null);
		}
		return allCustomers;
	}

	@Override
	public List<Customer> findByJobClassification(String jobClassification) {
		List<Customer> allCustomers = customerDetailsDao.findByJobClassification(jobClassification);
		return allCustomers;
	}

}

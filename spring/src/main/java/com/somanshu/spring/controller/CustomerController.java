/**
 * Controller class for /api flows
 */
package com.somanshu.spring.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somanshu.spring.model.Customer;
import com.somanshu.spring.service.CustomerService;
import com.somanshu.spring.util.ErrorResponseTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Description: Controller class to define routes for 
 * 					1. Get customer by first name
 * 					2. Find customers by region
 * 					3. Find customers by job classification
 * 				Defined as Rest controller for spring boot application.
 * 				Injecting service object for accessing DAO class
 * @author 		Somanshu Kalra
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "Corptec Code Challenge APIs")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Request type: 	GET
	 * Description: 	Method used for defining route for fetching customer information by first name.
	 * 					Responds with either a single or multiple customers having the same first name.
	 * 					Returns 400 http response code in case of incorrect param. 
	 * @param name
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Find customer/customers by first name", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/getCustomer/{name}")
	public ResponseEntity<?> findByName(
			@ApiParam(value = "First name of the customer", required = true) 
			@PathVariable("name") String name) {

		List<Customer> customer = customerService.findByName(name);

		//Check for invalid response
		if(null != customer && customer.size() > 0) 
			return ResponseEntity.status(HttpStatus.SC_OK).body(customer);
		else {				
			ErrorResponseTemplate errorResponse = 
					new ErrorResponseTemplate(HttpStatus.SC_BAD_REQUEST, LocalDateTime.now(), "Invalid Parameter");
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(errorResponse);
		}
	}

	/**
	 *  Request type: 	GET
	 *  Description: 	Method used for defining route for fetching customer information by region.
	 * 					Responds with multiple customers having the same region.
	 * 					Returns 400 http response code in case of incorrect param. 
	 * @param region
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Find customer/customers by region", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/findByRegion/{region}")
	public ResponseEntity<?> findByRegion(
			@ApiParam(value = "Region to be segregated by", required = true) 
			@PathVariable("region") @NotBlank @Size(max = 20) String region){ 
		
		List<Customer> allCustomers = customerService.findByRegion(region); 
		if(null != allCustomers && allCustomers.size() > 0) {			
			Map<String, List<Customer>> map = new HashMap<String, List<Customer>>();
			map.put(region, allCustomers);
			return ResponseEntity.status(HttpStatus.SC_OK).body(map);
		}
		else {
			ErrorResponseTemplate errorResponse = 
					new ErrorResponseTemplate(HttpStatus.SC_BAD_REQUEST, LocalDateTime.now(), "Invalid Parameter");
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(errorResponse);
		}
	}


	/**
	 *	Request type: 	GET
	 *  Description: 	Method used for defining route for fetching customer information by job classification.
	 * 					Responds with multiple customers having the same job classification.
	 * 					Returns 400 http response code in case of incorrect param. 
	 * @param jobClassification
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Find customer/customers by job classification", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping("/findByJobClassification/{jobClassification}")
	public ResponseEntity<?> findByJobClassification(
			@ApiParam(value = "Job classification to be segregated by", required = true)
			@PathVariable("jobClassification") @NotBlank @Size(max = 15) String jobClassification){
		
		List<Customer> allCustomers = customerService.findByJobClassification(jobClassification); 
		if(null != allCustomers && allCustomers.size() > 0) {			
			Map<String, List<Customer>> map = new HashMap<String, List<Customer>>();
			map.put(jobClassification, allCustomers);
			return ResponseEntity.status(HttpStatus.SC_OK).body(map);
		}
		else {
			ErrorResponseTemplate errorResponse = 
					new ErrorResponseTemplate(HttpStatus.SC_BAD_REQUEST, LocalDateTime.now(), "Invalid Parameter");
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(errorResponse);
		}
	}

}

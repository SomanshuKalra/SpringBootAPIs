/**
 * 
 */
package com.somanshu.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Description: Model class for customers. Used for creating a table in database by Hibernate.  
 * @author 		Somanshu Kalra
 * Date:		13/05/2020
 *
 */
@Entity
@Table(name="customer")
public class Customer {

	@Id
	@Column(name= "customer_id")
	private String customerId;
	
	@NotBlank(message = "First name is mandatory")
	@Column(name = "customer_first_name" )
	private String customerFirstName;
	
	@Column(name = "customer_surname")
	private String customerSurname;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "region")
	@JsonInclude(Include.NON_NULL)
	@NotBlank(message = "Region is mandatory")
	private String region;
	
	@NotBlank(message = "Job classification is mandatory")
	@Column(name = "job_classification")
	private String jobClassification;
	
	@Column(name = "date_joined")
	private String dateJoined;
	
	@Column(name = "balance")
	private float balance;


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getJobClassification() {
		return jobClassification;
	}

	public void setJobClassification(String jobClassification) {
		this.jobClassification = jobClassification;
	}

	public String getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerFirstName=" + customerFirstName + ", customerSurname="
				+ customerSurname + ", gender=" + gender + ", age=" + age + ", region=" + region
				+ ", jobClassification=" + jobClassification + ", dateJoined=" + dateJoined + ", balance=" + balance
				+ "]";
	}
	
}

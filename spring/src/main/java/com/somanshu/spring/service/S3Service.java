/**
 * 
 */
package com.somanshu.spring.service;

/**
 * Description: Interface to define S3Service class methods for interacting with AWS.
 * @author 		Somanshu Kalra
 * Date: 		13/05/2020
 * 
 */
public interface S3Service {
	
	//Method to fetch file from S3 and populate the database
	String downloadFile(String filename, String bucketname);
	
}

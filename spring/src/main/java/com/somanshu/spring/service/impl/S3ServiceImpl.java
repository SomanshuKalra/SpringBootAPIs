/**
 * 
 */
package com.somanshu.spring.service.impl;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.somanshu.spring.model.Customer;
import com.somanshu.spring.service.CustomerService;
import com.somanshu.spring.service.S3Service;


/**
 * Description: Service class for download data file from S3 and populate the databae. Contains 
 * 				business logic for readin excel file and populating the Customer object to be 
 * 				saved in the database. Uses object of DAO to interact with database.
 *  
 * @author 		Somanshu Kalra
 * Date:		13/05/2020
 *
 */
@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private AmazonS3 amazonS3Client;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public String downloadFile(String filename, String bucketname) {

		S3Object s3Object = amazonS3Client.getObject(bucketname, filename);
		S3ObjectInputStream stream = s3Object.getObjectContent();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(stream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = sheet.getRow(i);
				Customer customer = new Customer();
				customer.setCustomerId(Double.toString(row.getCell(0).getNumericCellValue()));
				customer.setCustomerFirstName(row.getCell(1).getStringCellValue());
				customer.setCustomerSurname(row.getCell(2).getStringCellValue());
				customer.setGender(row.getCell(3).getStringCellValue());
				customer.setAge((int)row.getCell(4).getNumericCellValue());
				customer.setRegion(row.getCell(5).getStringCellValue());
				customer.setJobClassification(row.getCell(6).getStringCellValue());
				customer.setDateJoined(row.getCell(7).getStringCellValue());
				customer.setBalance((float)row.getCell(8).getNumericCellValue());
				
				Customer savedCustomer = customerService.save(customer);
			}
			workbook.close();
			s3Object.close();
			return "Success!";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;


	}

}

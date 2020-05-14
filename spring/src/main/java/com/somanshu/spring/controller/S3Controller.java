/**
 * 
 */
package com.somanshu.spring.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somanshu.spring.service.S3Service;
import com.somanshu.spring.util.ErrorResponseTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Description: Controller class to define routes for 
 * 					1. Downloading excel file from S3 bucket
 * 					
 * 				Defined as Rest controller for spring boot application.
 * 				Injecting service object for accessing DAO class
 * @author 		Somanshu Kalra
 * Date: 		13/05/2020
 *
 */
@RestController
@RequestMapping("/s3")
@Api(value = "File donwload from S3 and populate database")
public class S3Controller {

	@Autowired
	private S3Service s3Service; 

	@Autowired
	private Environment env;

	@GetMapping("/download")
	@ApiOperation(value = "Download file and populate the database", response = ResponseEntity.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully populated database"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	public ResponseEntity<?> downloadFile(
			@ApiParam(value = "Filename to be downloaded", required = true)  
			@RequestParam("filename") String fileName) throws IOException{

		String data = s3Service.downloadFile(fileName, env.getProperty("cloud.s3.bucketName"));
		if(null != data)
			return ResponseEntity
					.status(HttpStatus.SC_OK)
					.body(data);
		else {
			ErrorResponseTemplate errorResponse = 
					new ErrorResponseTemplate(HttpStatus.SC_INTERNAL_SERVER_ERROR, LocalDateTime.now(), "Internal Server Error");
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(errorResponse);
		}
	}

}

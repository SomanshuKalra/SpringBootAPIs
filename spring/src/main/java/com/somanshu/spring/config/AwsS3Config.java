/**
 * Configuration class for AWS
 */
package com.somanshu.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Description: Class to configure AWS client. Uses access key and secret 
 * 				key to generate credentials and create an AWS client 
 * 				for downloading excel sheet and populating database.
 * Date: 		13/05/2020
 * @author		Somanshu Kalra
 *
 */
@Configuration
public class AwsS3Config {

	@Value("${cloud.s3.access.key}")
	private String accessKey;
	
	@Value("${cloud.s3.access.secret}")
	private String secretKey;
	
	@Bean
	public AmazonS3 generateAmazonS3Client(	@Value("${cloud.s3.region}")String region) {

		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(region)
				.build();
	}
}


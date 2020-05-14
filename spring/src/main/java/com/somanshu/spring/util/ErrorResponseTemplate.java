/**
 * 
 */
package com.somanshu.spring.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: Class to create a template for error responses. Sets status codes, current time
 * 				and message associated with the error.
 * 
 * @author 		Somanshu Kalra
 * Date: 		13/05/2020
 *
 */
public class ErrorResponseTemplate {

	private int httpStatus;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateTime;
	
	private String message;

	//Parameterized constructor
	public ErrorResponseTemplate(int httpStatus, LocalDateTime dateTime, String message) {
		this.httpStatus = httpStatus;
		this.dateTime = dateTime;
		this.message = message;
	}
	
	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponseTemplate [status=" + httpStatus + ", dateTime=" + dateTime + ", message=" + message
				+ "]";
	}
	
	
}

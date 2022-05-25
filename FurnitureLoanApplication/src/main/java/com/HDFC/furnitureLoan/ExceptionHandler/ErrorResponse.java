package com.HDFC.furnitureLoan.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private int status;
	private HttpStatus error;
	private String msg;
	private Date date;
	private String path;


}

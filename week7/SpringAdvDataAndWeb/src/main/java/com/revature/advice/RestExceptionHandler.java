package com.revature.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.errorhandling.ApiError;
import com.revature.errorhandling.ApiValidationError;
import com.revature.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	
	/*
	 * Intercepts exceptions that are cause by Invalid JSON
	 * 
	 * Might send back a 4XX series
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON Request";
		
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
		
	}
	
	// handle method not valid
	/*
	 * intercept exceptions triggered by those Hibernate/Javax validations.  @Length or @Pattern or @Email
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = "Request failed validation";
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		
		for(FieldError e : ex.getFieldErrors()) { 
			
			apiError.addSubError(new ApiValidationError(e.getObjectName(), e.getDefaultMessage(), e.getField(), e.getRejectedValue()));
			
		}
		
		return  buildResponseEntity(apiError);
	}
	
	
	// handleUserNotFoundExcpetion
	/*
	 * The below handler will intercept CUSTOM business logic exceptions/scenarios
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

		String error = "No User Found";
		
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));

	}
	
	
}

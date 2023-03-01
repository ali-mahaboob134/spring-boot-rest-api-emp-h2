package com.emp.rest.api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeAlreadyExistException.class)
	public ResponseEntity<Object> employeeAlreadyExistException(EmployeeAlreadyExistException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgumentException(MethodArgumentNotValidException ex){
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
		.forEach(e -> errorMap.put(e.getField(), e.getDefaultMessage()));
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}

}

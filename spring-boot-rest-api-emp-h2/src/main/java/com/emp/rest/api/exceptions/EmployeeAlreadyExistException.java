package com.emp.rest.api.exceptions;

public class EmployeeAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeAlreadyExistException(String msg) {
		super(msg);
	}

}

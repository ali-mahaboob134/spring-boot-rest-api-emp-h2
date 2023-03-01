package com.emp.rest.api.dto;

import com.emp.rest.api.validation.ValidateCity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
	
	@NotBlank (message="must not be null or empty") 
	@Size(min=1, max=10, message="must be min of 1 and max of 10 chars") 
	@Pattern(regexp = "^[A-Z0-9]+$" , message = "only alphanumeric with caps is allowed")
	private String emp_id;
	
	@NotBlank(message = "must not be null or empty")
	@Size(min=3, max=10, message="must be min of 3 and max of 10 chars") 
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "only alphanumeric is allowed")
	private String first_name;
	
	@NotBlank (message="must not be null or empty")
	@Size(min=3, max=10, message="must be min of 3 and max of 10 chars.") 
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "only alphanumeric is allowed")
	private String last_name;
	
	@Email(message="Email address is not valid.")
	private String email_id;
	
	@NotNull @Size(min=3, max=20) 
	@ValidateCity(message="Invalid city. Either Austin or San Fransisco or Chicago is allowed")
	private String city;
	
	public EmployeeDto() {
		
	}
	
	public EmployeeDto(String emp_id, String first_name, String last_name, String email_id, String city) {
		super();
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.city = city;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}

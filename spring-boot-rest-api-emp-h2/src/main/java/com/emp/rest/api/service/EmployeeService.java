package com.emp.rest.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.rest.api.dto.EmployeeDto;
import com.emp.rest.api.exceptions.EmployeeAlreadyExistException;
import com.emp.rest.api.exceptions.EmployeeNotFoundException;
import com.emp.rest.api.model.Employee;
import com.emp.rest.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public EmployeeDto saveEmployee(EmployeeDto empDto) {
		Employee existEmp = empRepo.findById(empDto.getEmp_id()).orElse(null);
		
		if(existEmp !=null && existEmp.getEmp_id().equals(empDto.getEmp_id())) {
			throw new EmployeeAlreadyExistException("Employee with id: " + empDto.getEmp_id()+" already exists in database");
		}
	
		Employee emp = new Employee(empDto.getEmp_id(), empDto.getFirst_name(), empDto.getLast_name(), empDto.getEmail_id(), empDto.getCity());
		return convertEntityToDto(empRepo.save(emp));
	}
	
	public EmployeeDto getEmployeeById(String emp_id) {
		Employee emp = empRepo.findById(emp_id).orElse(null);
		if(emp == null)
			throw new EmployeeNotFoundException("Employee with id: " + emp_id + " does not exist in database");
		return convertEntityToDto(emp);
	}
	
	public List<EmployeeDto> getEmployeesByCity(String city) {
		return empRepo.findByCity(city)
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	
	public String deleteEmployee(String emp_id) {
		Employee emp = empRepo.findById(emp_id).orElse(null);
		if(emp == null)
			throw new EmployeeNotFoundException("Employee with id: " + emp_id + " does not exist in database");
		empRepo.deleteById(emp_id);
		return "Employee successfully deleted from database";
	}
	
	private EmployeeDto convertEntityToDto(Employee emp) {
		EmployeeDto empDto = new EmployeeDto();
		empDto.setEmp_id(emp.getEmp_id());
		empDto.setFirst_name(emp.getFirst_name());
		empDto.setLast_name(emp.getLast_name());
		empDto.setEmail_id(emp.getEmail_id());
		empDto.setCity(emp.getCity());
		return empDto;
	}
	

}

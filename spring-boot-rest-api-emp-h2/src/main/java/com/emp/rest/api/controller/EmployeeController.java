package com.emp.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.rest.api.dto.EmployeeDto;
import com.emp.rest.api.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto empDto) {
		EmployeeDto newEmp = empService.saveEmployee(empDto);
		return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
	}
	
	@GetMapping("/{emp_id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String emp_id) {
		EmployeeDto emp = empService.getEmployeeById(emp_id);
		return ResponseEntity.ok(emp);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployeesByCity(@RequestParam("city") String city) {
		List<EmployeeDto> empList = empService.getEmployeesByCity(city);
		return ResponseEntity.ok(empList);
	}
	
	@DeleteMapping("/{emp_id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String emp_id) {
		String delMessage = empService.deleteEmployee(emp_id);
		return ResponseEntity.ok(delMessage);
	}
	
}

package com.emp.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.rest.api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	List<Employee> findByCity(String city);
	
}

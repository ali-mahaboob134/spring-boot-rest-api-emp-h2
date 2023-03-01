package com.emp.rest.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.emp.rest.api.dto.EmployeeDto;
import com.emp.rest.api.model.Employee;
import com.emp.rest.api.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService empService;

	@MockBean
	private EmployeeRepository empRepo;

	@Test
	public void createEmployeeTest() {

		EmployeeDto empDto = new EmployeeDto("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());

		Employee emp = new Employee();
		emp.setEmp_id(empDto.getEmp_id());
		emp.setFirst_name(empDto.getFirst_name());
		emp.setLast_name(empDto.getLast_name());
		emp.setEmail_id(empDto.getEmail_id());
		emp.setCity(empDto.getCity());
		when(empRepo.save(Mockito.any())).thenReturn(emp);
		EmployeeDto newEmp = empService.saveEmployee(empDto);
		assertThat(newEmp.getEmp_id()).isEqualTo("EMP001");
	}

	@Test
	public void createEmployeeTestThrowsException() {
		EmployeeDto empDto = new EmployeeDto("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		Employee emp = new Employee("EMP001", "Paul", "Johnson", "paul.johnson@gmail.com", "San Fransisco");
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.of(emp));
		try {
			empService.saveEmployee(empDto);
		} catch (Exception e) {
			assertThat(e.getMessage())
					.isEqualTo("Employee with id: " + empDto.getEmp_id() + " already exists in database");
		}

	}

	@Test
	public void getEmployeeByIdTest() {
		Employee emp = new Employee("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.of(emp));
		EmployeeDto empObj = empService.getEmployeeById("EMP001");
		assertThat(empObj.getEmp_id()).isEqualTo("EMP001");
	}
	
	@Test
	public void getEmployeeByIdThrowsExceptionTest() {
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
		try {
			empService.getEmployeeById("EMP001");
		} catch (Exception e) {
			assertThat(e.getMessage())
			.isEqualTo("Employee with id: EMP001 does not exist in database");

		}
	}

	@Test
	public void getEmployeesByCityTest() {
		Employee emp = new Employee("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		List<Employee> empList = new ArrayList<>();
		empList.add(emp);
		when(empRepo.findByCity(Mockito.anyString())).thenReturn(empList);
		List<EmployeeDto> empDtoList = empService.getEmployeesByCity("Austin");
		assertThat(empDtoList.size()).isEqualTo(empList.size());
	}

	@Test
	public void deleteEmployeeByIdTest() {
		Employee emp = new Employee("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.of(emp));
		assertThat(empService.deleteEmployee("EMP001")).isEqualTo("Employee successfully deleted from database");
	}
	
	@Test
	public void deleteEmployeeByIdThrowsExceptionTest() {
		when(empRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
		try {
			empService.deleteEmployee("EMP001");
		} catch (Exception e) {
			assertThat(e.getMessage())
			.isEqualTo("Employee with id: EMP001 does not exist in database");

		}
	}

}

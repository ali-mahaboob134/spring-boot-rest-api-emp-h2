package com.emp.rest.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.emp.rest.api.dto.EmployeeDto;
import com.emp.rest.api.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService empService;

	@Test
	public void testCreateEmployee() throws Exception {
		EmployeeDto empDto = new EmployeeDto("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		String inputJson = objToJson(empDto);
		String URI="/v1/Employee/";
		when(empService.saveEmployee(Mockito.any(EmployeeDto.class))).thenReturn(empDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void testGetEmployeeById() throws Exception {
		EmployeeDto empDto = new EmployeeDto("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		String inputJson = objToJson(empDto);
		String URI="/v1/Employee/EMP001";
		when(empService.getEmployeeById(Mockito.anyString())).thenReturn(empDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetEmployeesByCity() throws Exception {
		EmployeeDto empDto = new EmployeeDto("EMP001", "John", "Conner", "john.conner@gmail.com", "Austin");
		List<EmployeeDto> empDtoList = new ArrayList<>();
		empDtoList.add(empDto);
		String inputJson = objToJson(empDtoList);
		String URI="/v1/Employee?city=Austin";
		when(empService.getEmployeesByCity(Mockito.anyString())).thenReturn(empDtoList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		String URI="/v1/Employee/EMP001";
		String msg = "Employee successfully deleted from database";
		when(empService.deleteEmployee(Mockito.anyString())).thenReturn(msg);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputMsg = response.getContentAsString();
		assertThat(outputMsg).isEqualTo(msg);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	private String objToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(obj);
	}
}

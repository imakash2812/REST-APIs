//package com.example.demo.junit.springboot;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//import org.assertj.core.error.ShouldHaveSameSizeAs;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.example.demo.model.Employee;
//import com.example.demo.service.EmployeeService;
//
//@WebMvcTest(EmployeeControllerUnitTest.class)
//public class EmployeeControllerUnitTest {
//
//	@MockBean
//	private EmployeeService employeeService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	void should_return_employee_list() throws Exception{
//		Employee employee=new Employee();
//		when(employeeService.findAll()).thenReturn(List.of(employee));
//		
//		mockMvc.perform(get("/employee/getemployees"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$", hasSize("1")))
//		.andExpect(jsonPath("$[0].id", is("1")))
//		.andExpect(jsonPath("$[0].name",is("Akash Maurya")))
//		.andExpect(jsonPath("$[0].department",is("Testing")))
//		.andExpect(jsonPath("$[0].phoneNo",is("9667646841")));
//	}
//
//	private Object hasSize(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}

package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.restservices.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMockMvcTests.class})
public class ControllerMockMvcTests {
	
	@Autowired
	MockMvc mockMvc;
	

	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	List<Employee>myEmployees;
	Employee employee;
	//Using mockMvc object we can access a stand alone setUp() method, In that setUp method we have to build the employeeController method which is executed before each test method started
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	// Now we will start to write test method for EmployeeController methods
	
	@Test
	@Order(1)
	public void test_getAllEmployees() throws Exception //Here we are not calling getAllEmployees() method directly instead of calling that we can call the request methods like (GET, POST,PUT,DELETE)
	{
		myEmployees = new ArrayList<Employee>();
		myEmployees.add(new Employee("1", "Akash", "Testing", "9667646841"));
		myEmployees.add(new Employee("2", "Vishal", "Management", "9678745434"));
		when(employeeService.getAllEmployees()).thenReturn(myEmployees); //Mocking
		//Now we have done mocking now we will calling the controller methods to invoke through the request methods instead of calling them directly
		
		this.mockMvc.perform(get("/employee/getemployees"))// it will go and identify particular method from the controller
			.andExpect(status().isFound())//we get a response in which status should be found
			.andDo(print());// used to print the response body
	}
	
	@Test
	@Order(2)
	public void test_getEmployeeById() throws Exception
	{
		employee=new Employee("2", "Anil", "Management","96674653453");
		String employeeID="2";
		when(employeeService.getEmployeeById(employeeID)).thenReturn(employee);
		
		this.mockMvc.perform(get("/employee/getemployees/{id}",employeeID))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value("2"))
		.andExpectAll(MockMvcResultMatchers.jsonPath(".name").value("Anil"))
		.andExpect(MockMvcResultMatchers.jsonPath(".department").value("Management"))
		.andExpect(MockMvcResultMatchers.jsonPath(".phoneNo").value("96674653453"))
		.andDo(print());

	}
	
	@Test
	@Order(3)
	public void test_getEmployeeByName() throws Exception
	{
		employee=new Employee("2", "Anil", "Management","96674653453");
		String employeeName="Anil";
		when(employeeService.getEmployeeByName(employeeName)).thenReturn(employee);
		
		this.mockMvc.perform(get("/employee/getemployees/name").param("name","Anil"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value("2"))
		.andExpectAll(MockMvcResultMatchers.jsonPath(".name").value("Anil"))
		.andExpect(MockMvcResultMatchers.jsonPath(".department").value("Management"))
		.andExpect(MockMvcResultMatchers.jsonPath(".phoneNo").value("96674653453"))
		.andDo(print());
	}
	
	@Test
	@Order(4)
	public void test_addEmployee() throws Exception
	{
		employee=new Employee("2", "Aniket", "Gaming","9667465345");
		when(employeeService.addEmployee(employee)).thenReturn(employee);
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(employee);
		
		this.mockMvc.perform(post("/employee/addemployee")
		.content(jsonbody)
		.contentType(MediaType.APPLICATION_JSON))
.andExpect(status().isCreated())
.andDo(print());
	}
	
	@Test
	@Order(5)
	public void test_updateEmployee() throws Exception
	{
		employee=new Employee("2", "Suraj", "Gaming","9667465345");
		String employeeID="2";
		
		when(employeeService.getEmployeeById(employeeID)).thenReturn(employee); //Mocking
		when(employeeService.updateEmployee(employee)).thenReturn(employee);
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonbody=mapper.writeValueAsString(employee);
		
		this.mockMvc.perform(put("/employee/updateemployee/{id}",employeeID)
		.content(jsonbody)
		.contentType(MediaType.APPLICATION_JSON))
.andExpect(status().isOk())
.andExpect(MockMvcResultMatchers.jsonPath(".name").value("Suraj"))
.andExpect(MockMvcResultMatchers.jsonPath(".department").value("Gaming"))
.andExpect(MockMvcResultMatchers.jsonPath(".phoneNo").value("9667465345"))
.andDo(print());
	}
	
	
	@Test
	@Order(6)
	public void test_deleteEmployee() throws Exception
	{
		employee=new Employee("2", "Suraj", "Gaming","9667465345");
		String employeeID="2";
		
		when(employeeService.getEmployeeById(employeeID)).thenReturn(employee); //Mocking
		
		this.mockMvc.perform(delete("/employee/deleteemployee/{id}",employeeID))
.andExpect(status().isOk());
	}	
}

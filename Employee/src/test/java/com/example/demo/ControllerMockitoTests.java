package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes={ControllerMockitoTests.class})
@AutoConfigureMockMvc
public class ControllerMockitoTests {

	// For Controller Layer Unit Testing Service layer is acts as a External dependency so here we mocked the EmployeeService
	
	@Mock
	EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	//Here we are creating 2 variables because we want to prepare some mock data
	List<Employee> myEmployees; // In this myEmployee variable I will add multiple employees information
	Employee employee; // so if we want to delete a employee, add an employee and get an employee so single object we need
	
	
	@Test
	@Order(1)
	public void test_getAllEmployees() {
		myEmployees = new ArrayList<>();
		myEmployees.add(new Employee("1", "Akash", "Testing", "9667646841"));
		myEmployees.add(new Employee("2", "Vishal", "Management", "9678745434"));
		when(employeeService.getAllEmployees()).thenReturn(myEmployees); //Mocking
		ResponseEntity<List<Employee>> res= employeeController.getAllEmployees();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	
//	@Test
//	@Order(2)
//	public void test_test_getEmployeeById() {
//		employee=new Employee("1", "Akash", "Testing", "9667646841");
//		String employeeID="2";
//		
//		System.out.println(employeeService.getEmployeeById(employeeID).getId());
//		
//		assertEquals(employeeService.getEmployeeById(employeeID).getId(),employeeID);
//		
//	}
	
	@Test
	@Order(3)
	public void test_getEmployeeByName() {
		employee=new Employee("1", "Akash", "optimize", "9776464343");
		String employeeName= "Akash";
		
		when(employeeService.getEmployeeByName(employeeName)).thenReturn(employee);
		ResponseEntity<Employee> res=employeeController.getEmployeeByName(employeeName);
		
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(employeeName,res.getBody().getName());
		
	}
	
	@Test
	@Order(4)
	public void test_addEmployee() {
		employee=new Employee("3","Amit", "Salesforce", "9354414498");
		
		when(employeeService.addEmployee(employee)).thenReturn(employee);
		ResponseEntity<Employee> res=employeeController.addEmployee(employee);
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		assertEquals(employee,res.getBody());
	}
	
	@Test
	@Order(5)
	public void test_updateEmployee()
	{
		employee=new Employee("3", "Akash", "optimize", "9776464343");
		String employeeID="3";
		
		when(employeeService.getEmployeeById(employeeID)).thenReturn(employee);
		when(employeeService.updateEmployee(employee)).thenReturn(employee);
		
		ResponseEntity<Employee> res= employeeController.updateEmployee(employeeID, employee);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals("3", res.getBody().getId());
		assertEquals("Akash", res.getBody().getName());
		assertEquals("optimize",res.getBody().getDepartment());
	}
	
	@Test
	@Order(6)
	public void test_deleteEmployee() {
	    String employeeID = "3";
	    ResponseEntity<Void> res = employeeController.deleteEmployeeById(employeeID);
	    assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	
}

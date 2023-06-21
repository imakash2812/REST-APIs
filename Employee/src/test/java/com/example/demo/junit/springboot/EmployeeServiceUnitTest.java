package com.example.demo.junit.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

/**
 * Test Service Layer using Mockito
 * 
 * we are going to test our service EmployeeService. Since it uses EmployeeRepository as a dependency, we will need to mock it first.

Simply put, Mockito is a powerful mocking framework that takes testing to the next level.

Fortunately, JUnit 5 comes with a ready-to-use extension model that supports Mockito.

To enable Mockito integration, we need to annotate our test class with the @ExtendWith(MockitoExtension.class) annotation:
 * */


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceUnitTest {
	
	@Mock	//@Mock allows us to create and inject a mock of EmployeeRepository
	private EmployeeRepository employeeRepository;
	
	@InjectMocks	//@InjectMocks is used to create an instance of our service EmployeeServiceImpl so that we can test it.
	private EmployeeService employeeService;
	
	@Test
	void save_should_insert_new_employee() {
		
		//Given
		Employee employee=this.buildTestingEmployee();
		
		//When
		this.employeeService.save(employee);
		
		//Then
		verify(this.employeeRepository).save(employee);
	}

	@Order(1)
	@Test
	void findAll_should_return_employee_list() {
		//Given
		Employee employee=this.buildTestingEmployee();
		
		//When
		when(employeeRepository.findAll()).thenReturn(List.of(employee));
		List<Employee> employees=this.employeeService.findAll();
		
		//Then
		assertEquals(1,employees.size());
		verify(this.employeeRepository).findAll();
	}
	
	@Order(2)
	@Test
	void findById_should_return_employee() {
		
		//Given
		Employee employee=this.buildTestingEmployee();
		
		//when
		when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));
		Optional returnedEmployee=this.employeeService.findById("1");
		
		//Then
		assertEquals(employee.getId(),((Employee) returnedEmployee.get()).getId());
		verify(this.employeeRepository).findById("1");
	}
	
//	@Order(3)
//	@Test
//	void findByName_should_return_employee() {
//		
//		//Given
//		Employee employee=this.buildTestingEmployee();
//		
//		//when
//		when(employeeRepository.findByName("Akash Maurya")).thenReturn(Optional.of(employee));
//		Optional returnedEmployee=this.employeeService.findById("Akash Maurya");
//		
//		//Then
//		assertEquals(employee.getName(),((Employee) returnedEmployee.get()).getName());
//		verify(this.employeeRepository).findByName("Akash Maurya");
//	}
	
	@Order(4)
	@Test
	void deleteById_should_delete_employee() {
		//when
		this.employeeService.deleteEmployeeById("1");
		
		//Then
		verify(this.employeeRepository).deleteById("1");
	}
	
	
	private Employee buildTestingEmployee() {
		Employee employee=new Employee();
		employee.setId("1");
		employee.setName("Akash Maurya");
		employee.setDepartment("Testing");
		employee.setPhoneNo("9667646841");
		return employee;
	}

}

 package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {ServiceLayerMockitoTest.class})
@AutoConfigureMockMvc
public class ServiceLayerMockitoTest {
	
	//==========================================================
	/**
	 * This is the service layer testing for this layer repository layer is act as external dependency we are mocked the repository layer for writing the testcases for Service Layer
	 * 
	 * 
	 * For The controller layer testing this service layer is act as External Dependency 
	 * */
	
	//==========================================================
	

	@Mock  //We mock the employeerepository because because we are going to use it as a mocking purpose because its a external dependency
	private EmployeeRepository employeeRepository;
	
	@InjectMocks  // Here we are going to invoke all the methods which are present in the employee service 
	private EmployeeService employeeService;
	
	public List<Employee> myemployees; //variable
	
	@Test
	@Order(1)// because I want to execute this method first
	public void test_getAllEmployees() {
		List<Employee> myemployees = new ArrayList<>();
		myemployees.add(new Employee("1", "Akash", "Testing", "9667646841"));
		myemployees.add(new Employee("2", "Vishal", "Management", "9678745434"));

//		List<Employee>myemployees=new ArrayList<>();
//		myemployees.add(1,"Akash","Testing","9667646841");//mocking data stored in myemployee variable
//		myemployees.add(2,"Vishal","Management","9678745434");//mocking data stored in myemployee variable
		when(employeeRepository.findAll()).thenReturn(myemployees);//when repository method is called then instead of talking with the database to fetch the data we should return the mocking data
		assertEquals(2,employeeService.getAllEmployees().size());
	}
	
	@Test
	@Order(2)
	public void test_getEmployeeById() {
		List<Employee> myemployees = new ArrayList<>();
		myemployees.add(new Employee("1", "Akash", "Testing", "9667646841"));
		myemployees.add(new Employee("2", "Vishal", "Management", "9678745434"));
		String employeeID="1";
		when(employeeRepository.findAll()).thenReturn(myemployees);
		assertEquals(employeeID,employeeService.getEmployeeById(employeeID).getId());
	}
	
	
	@Test
	@Order(3)
	public void test_getEmployeeByName() {
		List<Employee> myemployees = new ArrayList<>();
		myemployees.add(new Employee("1", "Akash", "Testing", "9667646841"));
		myemployees.add(new Employee("2", "Vishal", "Management", "9678745434"));
		String employeeName="Akash";
		when(employeeRepository.findAll()).thenReturn(myemployees);
		assertEquals(employeeName,employeeService.getEmployeeByName(employeeName).getName());
	}
	
	
	@Test
	@Order(4)
	public void test_addEmployee() {
		Employee employee=new Employee("3", "Aniket", "IT", "7011722060");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee,employeeService.addEmployee(employee));
	}
	
	@Test
	@Order(5)
	public void test_updateEmployee() {
		Employee employee=new Employee("3", "Aniket", "IT", "7011722060");
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(employee,employeeService.updateEmployee(employee));
	} 
	
	
	@Test
	@Order(6)
	public void test_deleteEmployeeById() {
	    String id = "3";
	    employeeService.deleteEmployeeById(id);
	    verify(employeeRepository, times(1)).deleteById(id);
	}

	
	
	
}

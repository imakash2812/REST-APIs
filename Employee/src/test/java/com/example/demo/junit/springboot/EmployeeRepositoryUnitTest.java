package com.example.demo.junit.springboot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
@DataJpaTest	//Spring Boot provides the @DataJpaTest annotation to enhance testing functionality for JPA repositories. It automatically configure JPA repositories by annotating the unit test class with @DataJpaTest.
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class EmployeeRepositoryUnitTest {

	@Autowired
	 private EmployeeRepository employeeRepository;



	   @Test
	   @Order(1)
	   public void _should_add_employee() {
	    Employee employee = new Employee("2","Akash","Development","9911726433");
	    Employee persistedEmployee=this.employeeRepository.save(employee);
	    assertNotNull(persistedEmployee);
	    assertEquals("2",persistedEmployee.getId());
	    assertEquals("Akash",persistedEmployee.getName());
	    assertEquals("Development",persistedEmployee.getDepartment());
	    assertEquals("9911726433", persistedEmployee.getPhoneNo());
	 }
		    
	   @Test
	   @Order(2)
	   public void _should_return_employee_list() 
	   {
		   //When
		   List<Employee> employees=this.employeeRepository.findAll();
		   
		   //Then
		   assertEquals(9,employees.size());
		   
	   }
	   
	   @Test
	   @Order(3)
	   public void _should_return_employee_by_id()
	   {
		   //When
		   Optional<Employee> employees=this.employeeRepository.findById("2");
		   //Then
		   assertTrue(employees.isPresent());
	   }
	   
	   @Test
	   @Order(4)
	   public void _should_return_employee_by_name()
	   {
		   //When
		   Optional<Employee> employees=this.employeeRepository.findByName("Akash");
		   //Then
		   assertTrue(employees.isPresent());
	   }
	   
	   @Test
	   @Order(5)
	   public void _should_update_existing_employee()
	   {
		   //Given
		   Employee existingEmployee=new Employee();
		   existingEmployee.setId("8");
		   existingEmployee.setName("Arun");
		   existingEmployee.setDepartment("Accounts");
		   existingEmployee.setPhoneNo("9695478321");
		   //When
		   Employee updatedEmployee=this.employeeRepository.save(existingEmployee);
		   //Then
		   assertNotNull(updatedEmployee);
		   assertEquals("8", updatedEmployee.getId());
		   assertEquals("Arun", updatedEmployee.getName());
		   assertEquals("Accounts", updatedEmployee.getDepartment());
		   assertEquals("9695478321", updatedEmployee.getPhoneNo());

	   }
	   @Test
	   @Order(6)
	   public void _should_delete_employee_by_Id() 
	   {
		   //When
		 this.employeeRepository.deleteById("9"); 
		 Optional<Employee> employee=this.employeeRepository.findById("9");
		 //Then
		 assertFalse(employee.isPresent());
	   }

}

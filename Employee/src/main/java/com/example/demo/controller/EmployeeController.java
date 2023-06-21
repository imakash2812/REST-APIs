package com.example.demo.controller;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Value("${my.message}")
	private String message;
	
	@GetMapping("/message")
	public String getMessage() {
		return message;
	}
	
	@GetMapping("/getemployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
		List<Employee> employees= employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
//		return employeeService.getAllEmployees();
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
//		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/getemployees/{id}")
	public Employee getEmployeeById(@PathVariable(value="id") String id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/getemployees/name")
	public ResponseEntity<Employee> getEmployeeByName(@RequestParam(value="name") String name) {
		
		try {
			Employee employee= employeeService.getEmployeeByName(name);
			return new ResponseEntity<Employee>(employee,HttpStatus.FOUND);
			}
			catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
//		return employeeService.getEmployeeByName(name);
	}
	
	@PostMapping("/addemployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		try {
			employee=employeeService.addEmployee(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	
	}
	
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") String id, @RequestBody Employee employee) {
		try {
		Employee existingemployee=employeeService.getEmployeeById(id);
		existingemployee.setName(employee.getName());
		existingemployee.setDepartment(employee.getDepartment());
		existingemployee.setPhoneNo(employee.getPhoneNo());
		
		Employee updated_employee= employeeService.updateEmployee(existingemployee);
		return new ResponseEntity<Employee>(updated_employee,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		//		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable(value="id") String id) {
	    try {
	        employeeService.deleteEmployeeById(id);
	        return ResponseEntity.ok().build();
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	//Multipart File upload
	
	@GetMapping("/")
	public String index() {
		return "upload";
	}
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		employeeService.uploadFile(multipartFile);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + multipartFile.getOriginalFilename() + "!");
		return "redirect:/";
	}

	@PostMapping("/uploadFiles")
	public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
	 
	    Arrays.asList(files)
	        .stream()
	        .forEach(file -> employeeService.uploadFile(file));
	 
	    redirectAttributes.addFlashAttribute("message",
	        "You successfully uploaded all files!");
	 
	    return "redirect:/ ";
	}

}

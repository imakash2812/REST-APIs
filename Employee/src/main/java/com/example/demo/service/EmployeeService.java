package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.AddResponse;
import com.example.demo.exception.FileStorageException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
@Component
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

//	static HashMap<Integer, Employee> employeeIdMap;
	
//	public EmployeeService() {
//		employeeIdMap=new HashMap<Integer,Employee>();
//		
//		Employee akash=new Employee(1, "Akash", "Junior cloud Engineer", "9667646841");
//		Employee vishal=new Employee(2, "vishal", "Python Developer", "4657367353");
//		Employee Aditya=new Employee(3, "Aditya", "Android", "9575749374");
//		
//		employeeIdMap.put(1, akash);
//		employeeIdMap.put(2, vishal);
//		employeeIdMap.put(3, Aditya);
//		
//	}
	
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees= employeeRepository.findAll();
		return employees;
//		List employees=new ArrayList<>(employeeIdMap.values());
//		return employees;
	}
	
	public Employee getEmployeeById(String id) {
	    List<Employee> employees = employeeRepository.findAll();
	    System.out.print(employees);
	    Employee employee = null;

	    for (Employee emp : employees) {
	        if (emp.getId().equals(id)) {
	            employee = emp;
	            break;
	        }
	    }
	    return employee;
	}

	
	public Employee getEmployeeByName(String name) {
		List<Employee> emp=employeeRepository.findAll();
		Employee employee=null;
		
		for(Employee empl:emp) {
			if(empl.getName().equalsIgnoreCase(name))
				employee=empl;
		}
		return employee; 
//		Employee employee=null;
//		for(Stringi: employeeIdMap.keySet()) {
//			if(employeeIdMap.get(i).getName().equals(name));
//			employee=employeeIdMap.get(i);
//		}
//		return employee;
	}
	
//	public Employee addEmployee(Employee employee) {
//	    String maxId = getMaxId();
//	    employee.setId(maxId);
//	    employeeRepository.save(employee);
//	    return employee;
//	}

	public Employee addEmployee(Employee employee) {
	    String maxId = getMaxId();
	    employee.setId(maxId);
	    employeeRepository.save(employee);
	    return employee;
	}

	

		
//=======================================================		
//		employee.setId(getMaxId());
//		employeeIdMap.put(employee.getId(),employee);
//		return employee;
	//====================================================================================================
	//	Utility method to get MaxID

//public String getMaxId() {
//    return employeeRepository.findAll().stream()
//            .mapToInt(Employee::getId)
//            .max()
//            .orElse(0) + 1;
//}
public String getMaxId() {
    Optional<Employee> employee = employeeRepository.findTopByOrderByIdDesc();
    if(employee.isPresent()){
        return String.valueOf(Integer.parseInt(employee.get().getId()) + 1);
    }else{
        return "1";
    }
}

	
//	public StringgetMaxId() {
//		
//		return employeeRepository.findAll().size()+1;
////		Stringmax=0;
////		for(Stringid:employeeIdMap.keySet())
////			if(max<=id)
////				max=id;
////		return max+1;
//	}
////	
//	//====================================================================================================
	
	public Employee updateEmployee(Employee employee) {
		
		employeeRepository.save(employee);
		return employee;
		
//		if(employee.getId()>0)
//			employeeIdMap.put(employee.getId(), employee);
//		return employee;
	}

	public void deleteEmployeeById(String id) {
	    employeeRepository.deleteById(id);
	}

	public Employee save(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		return this.employeeRepository.findAll();
	}

	public Optional<Employee> findById(String id) {
		return this.employeeRepository.findById(id);
	}

	
	//========================================================================================================
	@Value("${app.upload.dir:${user.home}}")
	public Path uploadDir = Paths.get(System.getProperty("user.home"));

	public void uploadFile(MultipartFile multipartFile) {
		try {
			Path copyLocation = uploadDir.resolve(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
			Files.copy(multipartFile.getInputStream(),copyLocation, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new FileStorageException("File Not Found");
		}
	}
	
}

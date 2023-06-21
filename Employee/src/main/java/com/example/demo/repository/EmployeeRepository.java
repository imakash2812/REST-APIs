package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Optional<Employee> findTopByOrderByIdDesc();

	Optional<Employee> findById(String i);

	Optional<Employee> findByName(String string);

}

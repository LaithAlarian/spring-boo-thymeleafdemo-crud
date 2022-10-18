package com.crud.demo.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.dao.EmployeeRepository;
import com.crud.demo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeRepository employeeRepository;

	public EmployeeRestController(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee finddById(@PathVariable int employeeId)
	{
		Employee employee=null;
		Optional<Employee> optional=employeeRepository.findById(employeeId);
		if(optional.isPresent())
		{
			employee=optional.get();
		}
		else
		{
			new RuntimeException("No employee found !!!");
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee employee)
	{	
		employee.setId(0);
		employeeRepository.save(employee);
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee)
	{
		employeeRepository.save(employee);
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String delete(@PathVariable int employeeId)
	{
		Employee employee=finddById(employeeId);
		employeeRepository.delete(employee);
		return "Delete done.";
	}
	
	
	
	
	
	

}

package com.crud.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.dao.EmployeeRepository;
import com.crud.demo.entity.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	public EmployeeServiceImp(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		
		List<Employee> employees=employeeRepository.findAllByOrderByLastNameAsc();
		
		if(employees==null)
		{
			new RuntimeException("No employee to print !!!");
		}
		
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		
		Employee employee=null;
		
		Optional<Employee> optional=employeeRepository.findById(employeeId);
		if(optional.isPresent())
		{
			employee=optional.get();
		}else
		{
			new RuntimeException("No employee to print !!!");
		}
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		employeeRepository.save(employee);
		

	}

	@Override
	public void deleteById(int employeeId) {
		
		findById(employeeId);
		employeeRepository.deleteById(employeeId);

	}

}

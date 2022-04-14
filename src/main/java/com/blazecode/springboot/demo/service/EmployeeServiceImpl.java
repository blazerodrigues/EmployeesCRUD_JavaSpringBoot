package com.blazecode.springboot.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blazecode.springboot.demo.dao.EmployeeRepository;
import com.blazecode.springboot.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//We do not have to use @Transactional for any of the service method which are calling the DAO, because JpaRepository(which the DAO is extending) provides this  functionality
	
	//value will be set by the constructor
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	public List<Employee> findAll() {
//		return employeeRepository.findAll();
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		//employeeRepository.findById(theId) will return Optional<Employee> object. We have to check and convert it to Employee object
		Optional<Employee> result = employeeRepository.findById(theId);
		
		//check if Optional class has a value present(not null) or not(ie. null)
		Employee theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get(); //Extracts Employee from Optional. So to speak.
		}else {
			//employee not found
			throw new RuntimeException("Did not find employee id - "+theId);
		}
		
		return theEmployee;
		
	}

	@Override
	public void save(Employee theEmployee) {

		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteById(int theId) {

		employeeRepository.deleteById(theId);

	}

}

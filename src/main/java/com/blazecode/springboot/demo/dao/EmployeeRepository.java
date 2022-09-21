package com.blazecode.springboot.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazecode.springboot.demo.entity.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//interface can EXTEND another interface
//class can IMPLEMENT another interface
//JPA repository format: JpaRepository<EntityType,PrimaryKeyType>
//notice how, unlike other DAOs, we are not using @Repository annotation here. Bean is created by Spring boot as this interface ultimately extends the special Repository interface.
 	

	// This is it, no code required!

	
	//Example of JPA "Query-Methods" //Looks for a specific format and pattern. Creates appropriate query behind the scenes.
	//Add a method to sort by last name asc //findAllBy - OrderBy - LastName - Asc
	public List<Employee> findAllByOrderByLastNameAsc();
	
	
	
}

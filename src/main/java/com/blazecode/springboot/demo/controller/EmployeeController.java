package com.blazecode.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazecode.springboot.demo.entity.Employee;
import com.blazecode.springboot.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	/*Commenting In-Memory Employee list storage
	//load employee data (in the memory)
	private List<Employee> theEmployees;
	//@PostConstruct annotation method will run after the bean has been created by spring
	@PostConstruct
	private void loadData() {
		//create employees
		Employee emp1 = new Employee(1,"Leslie","Andrews","leslie@blazecode.com");
		Employee emp2 = new Employee(2,"Emma","Baumgarten","emma@blazecode.com");
		Employee emp3 = new Employee(1,"Avani","Gupta","avani@blazecode.com");
		
		//create the list
		theEmployees = new ArrayList<>();
		
		//add employees to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);		
	}
	*/
	
	//Setting up the SERVICE layer
	private EmployeeService employeeService;
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	//add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employees from the database using SERVICE layer
		List<Employee> theEmployees = employeeService.findAll();
		
		//add to the spring model
		theModel.addAttribute("employees",theEmployees);
		
		//this will look in the folder src/main/resources/templates
		return "employees/list-employees";
	}
	
	// employees/showFormForAdd ... when we click on "Add employee" button
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
	
		//create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		
		return "employees/employee-form";
		
	}
	
	// employees/showFormForUpdate ... when we click on "Update" Employee Button
	@GetMapping("/showFormForUpdate") //@RequestParam("parameterName") will pass the URL parameter to the method-parameter
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {
		//get the Employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		//set the retrieved employee as a ModelAttribute to pre-populate the form
		theModel.addAttribute("employee",theEmployee);
		
		//send over to the form
		return "employees/employee-form";
		
		
	}
	
	
	//save the employee Added by filling AddEmployee form
	@PostMapping("/save")  //@ModelAttribute("attributeName") this will pass the model-object which was submitted in the form as a method input-parameter
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the employee
		employeeService.save(theEmployee);
		
		//use a redirect (post-redirect-get technique) to prevent duplicate submissions
		//this will redirect to employees/list controller mapping
		return "redirect:/employees/list";
		
	}
	
	
	//delete the employee
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete the employee
		employeeService.deleteById(theId);
		
		//redirect to employees/list
		return "redirect:/employees/list";
		
	}
	
	
}

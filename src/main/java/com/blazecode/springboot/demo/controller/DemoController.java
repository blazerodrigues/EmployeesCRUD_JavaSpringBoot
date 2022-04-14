package com.blazecode.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	//Create a mapping for "/hello"
	@GetMapping("/hello")
	public String sayHello(Model theModel) {
		theModel.addAttribute("theDate",new java.util.Date());
		
		//Spring boot will configure to use thymeleaf based on the thymeleaf dependency that is added in the maven pom file
		//folder : src/main/resources/templates/helloworld.html
		return "helloworld"; 
	}
	
}

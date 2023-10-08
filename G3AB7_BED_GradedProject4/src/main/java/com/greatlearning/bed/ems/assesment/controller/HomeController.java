package com.greatlearning.bed.ems.assesment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.bed.ems.assesment.dto.UserRegistrationDto;
import com.greatlearning.bed.ems.assesment.model.NewEmployeeRegister;
import com.greatlearning.bed.ems.assesment.model.SearchOrderEmployee;
import com.greatlearning.bed.ems.assesment.service.UserServiceImpl;

@Controller
public class HomeController {

	
	@Autowired
	UserServiceImpl userServiceImplService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("listofAllEmployee", userServiceImplService.getAll());
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addEmp() {
		return "add_employee";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp( @ModelAttribute("user") UserRegistrationDto user ) {
		user.setPassword("123");
		user.setRole("USER");
		
		userServiceImplService.save(user);

		return "redirect:/";
	}
	
	 
	  @GetMapping("/deleteEmployee/{id}")
	  public String deleteEmployee(@PathVariable(value = "id") long id) {

	      
	      this.userServiceImplService.deleteEmployeeById(id);
	      return "redirect:/";
	  }
	 
	  
	  @GetMapping("/viewEmployee/{id}")
	  public String viewEmployee(@PathVariable(value = "id") long id, Model model) {

	      
		  NewEmployeeRegister newEmployee = userServiceImplService.getNewEmployeeById(id);

	      model.addAttribute("viewlogin", newEmployee);
	      return "view_employee";
	  }
	  
	  
	  @GetMapping("/employeeUpdateData/{id}")
	    public String employeeUpdateData(@PathVariable(value = "id") int id, Model model) {

	       
		  NewEmployeeRegister newEmployee = userServiceImplService.getEmployeeById(id);

	       
	        model.addAttribute("updateEmployee", newEmployee);
	        return "update_employee";
	    }
	  @PostMapping("/updateEmployee")
	  public String updateEmployee(@ModelAttribute("updateEmployee") NewEmployeeRegister newEmployee) {
	     
	  	
	  	this.userServiceImplService.updateUmployeeById(newEmployee);
	      return "redirect:/";
	  }
	  
	  
	  @PostMapping("/viewlogin")
	  public String viewloginEmployee() {

	    
	      return "redirect:/";
	  }
	  
	  @GetMapping("/searchByorder")
	  public String searchByOrder() {
		
		  return "searchByorder_employee";
	  }
	   
	  @PostMapping("/searchEmployeeorder")
	  public String searchEmployeeorder(@ModelAttribute("orderBy") String searchByorder,Model model) {
		System.out.println("searchByorder "+searchByorder);
		
		model.addAttribute("listofAllOrderEmployee", userServiceImplService.orderByEmployeeSearch(searchByorder));
		  return "searchByorder_employee";
	  }
	  
	  @GetMapping("/searchEmp/{query}")
	 public ResponseEntity<List<NewEmployeeRegister>> serchemp(@PathVariable("query") String query) {
		  
		List<NewEmployeeRegister> emp = userServiceImplService.searchemp(query);
		System.out.println(emp);
		 return ResponseEntity.ok(emp);
	 }
	   
	
}

package com.greatlearning.bed.ems.assesment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.bed.ems.assesment.dto.UserRegistrationDto;
import com.greatlearning.bed.ems.assesment.model.NewEmployeeRegister;
import com.greatlearning.bed.ems.assesment.model.Role;
import com.greatlearning.bed.ems.assesment.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user")  UserRegistrationDto registrationDto ) {


		System.out.println(registrationDto);
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
	
	
	
}

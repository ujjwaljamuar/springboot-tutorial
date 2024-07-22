package com.smartcontact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontact.dao.UserRepository;
import com.smartcontact.entities.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";

	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";

	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";	
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user")User user, @RequestParam(value ="agreement", defaultValue = "false") boolean agreement, Model model) {
		
		if(!agreement) {
			System.out.println("Please check the agreement.");
			return "signup";
		}
		System.out.println(agreement);
		System.out.println(user);
		
		User result = userRepository.save(user);
		System.out.println(result);
		
		model.addAttribute("user", result);
		return "signup";
	}
}

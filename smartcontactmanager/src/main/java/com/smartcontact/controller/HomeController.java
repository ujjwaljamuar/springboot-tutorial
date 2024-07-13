package com.smartcontact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contract Manager");
		return "home";
		
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("About", "Home - Smart Contract Manager");
		return "about";
		
	}
}

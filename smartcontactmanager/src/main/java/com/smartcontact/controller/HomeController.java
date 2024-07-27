package com.smartcontact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontact.dao.UserRepository;
import com.smartcontact.entities.User;
import com.smartcontact.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	public String registerUser(
			@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
			Model model,
			HttpSession session) {

		try {
			if (!agreement) {
				System.out.println("Please check the agreement.");
				throw new Exception("Please check the agreement.");
			}
			
			if(result.hasErrors()) {
				System.out.println("ERROR " + result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("admin");
			user.setImageUrl("image.png");
			user.setEnabled(true);

			User resultUser = userRepository.save(user);
			System.out.println(resultUser);

			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Success ! ", "alert-success"));
			
			return "signup";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong ! " + e.getMessage(), "alert-danger"));
			return "signup";
		}

		
	}
}

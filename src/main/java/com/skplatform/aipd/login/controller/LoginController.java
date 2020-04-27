package com.skplatform.aipd.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skplatform.aipd.entity.UserEntity;
import com.skplatform.aipd.repository.primary.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepo;

	@RequestMapping("/")
	public String mainForm(Model model) {
		List<UserEntity> users = userRepo.findAll();
		model.addAttribute("users", users);
		users.stream().forEach(System.out::println);
		return "login/LoginForm";
	}
	
	@RequestMapping("/addUser")
	public String addUser(Model model) {
		UserEntity users = new UserEntity();
		users.setUsername("pgmman1");
		users.setLastName("1");
		users.setFirstName("1");
		userRepo.save(users);
		model.addAttribute("TESTDATA", "HELLO");
		return "login/LoginForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		List<UserEntity> users = userRepo.findAll();
		model.addAttribute("TESTDATA", "HELLO");
		return "login/LoginForm";
	}
}

package com.skplatform.aipd.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skplatform.aipd.entity.maria.UserEntity;
import com.skplatform.aipd.entity.neo4j.DiseaseEntity;
import com.skplatform.aipd.entity.neo4j.Drug;
import com.skplatform.aipd.repository.maria.UserRepository;
import com.skplatform.aipd.repository.neo4j.DiseaseRepository;
import com.skplatform.aipd.repository.neo4j.DrugRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DiseaseRepository dieseaseRepo;

	@Autowired
	private DrugRepository drugRepo;
	
	@RequestMapping("/")
	public String mainForm(Model model) {
		List<UserEntity> users = userRepo.findAll();
		List<Drug> drug = (List<Drug>) drugRepo.findAll();
		List<DiseaseEntity> disease = (List<DiseaseEntity>) dieseaseRepo.findAll();
		
		model.addAttribute("users", users);
		users.stream().forEach(System.out::println);
		drug.stream().forEach(System.out::println);
		//disease.stream().forEach(System.out::println);
		return "login/LoginForm";
	}
	
	@RequestMapping("/addUser")
	public String addUser(Model model) {
		UserEntity users = new UserEntity();
		users.setUsername("pgmman2");
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

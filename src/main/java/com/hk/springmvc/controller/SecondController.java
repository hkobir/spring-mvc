package com.hk.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.springmvc.models.Employ;
import com.hk.springmvc.repository.EmployRepo;

@Controller
public class SecondController {
	@Autowired
	EmployRepo employRepo;

	// save to database
	@PostMapping("/saveEmploy") // only pass data by post method
	public String saveEmploy(@ModelAttribute Employ employ) {
//				System.out.println(pId + ", " + pName + ", " + ", " + pSp);
		employRepo.save(employ);
		return "redirect:/home";
	}

	@GetMapping("/findEmployBySpec")
	public String findEmployBySpec( String spec, Model model) {
		List<Employ> employees = employRepo.findByspec(spec); //method name should be maintain by variable name
		model.addAttribute("programmers", employees);

		return "all_programmers.html";

	}
	
	@GetMapping("/findEmployByName")
	public String findEmployByName( String name, Model model) {
		List<Employ> employees = employRepo.findE(name);
		model.addAttribute("programmers", employees);

		return "all_programmers.html";

	}
	
	@GetMapping("/findEmployById")
	public String findEmployById(@RequestParam("eId") int id, Model model) {
		Employ e = employRepo.getById(id);
		model.addAttribute("employ", e);

		return "profile.html";

	}

	@GetMapping("/deleteEmployById")
	public String deleteEmployById(@RequestParam("eId") int id) {
		employRepo.deleteById(id);
		return "redirect:/home";

	}
	
	@PostMapping("/updateEmploy") // only pass data by post method
	public String updateEmploy(@ModelAttribute Employ employ) {
		Employ e = employRepo.getById(employ.getId());
		
		e.setName(employ.getName());
		e.setSpec(employ.getSpec());
	
		employRepo.save(e);
		return "profile.html";
	}

}

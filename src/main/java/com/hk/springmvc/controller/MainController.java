package com.hk.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.hk.springmvc.models.Employ;
import com.hk.springmvc.repository.EmployRepo;

@Controller
@ControllerAdvice // all Model attribute are now available for all controller
public class MainController {
	@Autowired
	EmployRepo employRepo;

	// model using method
	@ModelAttribute
	public void greeting(Model model) {
		model.addAttribute("msg", "Welcome to this heaven!");
	}

	@GetMapping("/home")
	public String homePage() {
		return "home.html";
	}

	// using ModelAndView raw data
	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public ModelAndView saveData(@RequestParam("pId") int id, @RequestParam("pName") String name,
			@RequestParam("pSp") String spec) {
//		System.out.println(pId + ", " + pName + ", " + ", " + pSp);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile.html");
		mv.addObject("id", id);
		mv.addObject("name", name);
		mv.addObject("spec", spec);
		return mv;
	}

	// using Model class attribute
	@PostMapping("/saveEmployData") // only pass data by post method
	public ModelAndView saveEmployData(@ModelAttribute Employ employ) {
//		System.out.println(pId + ", " + pName + ", " + ", " + pSp);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile.html");
		return mv;
	}

	

	@GetMapping("/employees")
	public String getAllProgrammer(Model model) {
		List<Employ> employees = new ArrayList<Employ>();
		employees.add(new Employ(001, "Nazim", "Developer"));
		employees.add(new Employ(002, "Nar", "Deper"));
		employees.add(new Employ(003, "Nazr", "Deloper"));
		employees.add(new Employ(004, "Naze", "Dloper"));
		employees.add(new Employ(005, "Naem", "loper"));
		model.addAttribute("programmers", employees);
		return "all_programmers.html";
	}

}

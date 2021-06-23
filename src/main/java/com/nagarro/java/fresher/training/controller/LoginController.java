package com.nagarro.java.fresher.training.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.java.fresher.training.modals.Employee;
import com.nagarro.java.fresher.training.modals.User;
import com.nagarro.java.fresher.training.repositories.LoginRepo;

@RestController
@RequestMapping("/")
public class LoginController {

	@Autowired
	LoginRepo repo;
	@GetMapping("login")
	public ModelAndView login(User user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", user);
		mv.setViewName("login");
		return mv;

	}

	@GetMapping("ListEmployee")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ListEmployee");
		return mv;

	}

	@PostMapping("login")
	public ModelAndView Login(User user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", user);
		if (repo.findByUserIDAndPassword(user.getUserID(), user.getPassword()).isEmpty())
			mv.setViewName("login");
		else {

			RestTemplate restTemplate = new RestTemplate();
			Employee[] empdata = restTemplate.getForObject("http://localhost:8081/ListEmployee", Employee[].class);
			Iterable<Employee> empl = Arrays.asList(empdata);
			mv.addObject("obj1", empl);
			mv.setViewName("ListEmployee");
		}
		return mv;

	}

	 @GetMapping("AddEmployee")
	public ModelAndView add(Employee employee) {
	ModelAndView mv = new ModelAndView();
	mv.addObject("obj", employee);
	mv.setViewName("AddEmployee");
	return mv;

	 }
	 @PostMapping("AddEmployee")
	public ModelAndView addData(Employee employee) {
	ModelAndView mv = new ModelAndView();
	RestTemplate restTemplate = new RestTemplate();
	Employee[] empdata = restTemplate.postForObject("http://localhost:8081/addEmployee", employee,Employee[].class);
	Iterable<Employee> emp2 = Arrays.asList(empdata);
	mv.addObject("obj2", emp2);
	mv.setViewName("AddEmployee");
	return mv;

	 }
	 @GetMapping("UpdateEmployee")
	 public ModelAndView updateData(@RequestParam("id") int employeeId,Employee employee) {
	 ModelAndView mv = new ModelAndView();
	 mv.setViewName("UpdateEmployee");
	 RestTemplate restTemplate = new RestTemplate();
		Employee empdata = restTemplate.postForObject("http://localhost:8081/UpdateEmployee/?id="+employeeId,employee, Employee.class);
	 mv.addObject("obj", empdata);
	 return mv;

	  }
	 @PostMapping("UpdateEmployee")
		public ModelAndView update(@RequestParam("id") int employeeId,Employee employee) {
		ModelAndView mv = new ModelAndView();
		RestTemplate restTemplate = new RestTemplate();
		Employee empdata = restTemplate.postForObject("http://localhost:8081/UpdateEmployee/?id="+employeeId, employee,Employee.class);
		Iterable<Employee> emp2 = Arrays.asList(empdata);
		mv.addObject("obj1", emp2);
		mv.setViewName("UpdateEmployee");
		return mv;

		 }
	

	 }


package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.entity.Employee;
import com.emp.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

	@Autowired
	private EmpService service;
	
	@GetMapping("/")
	public String home(Model m) {
		
		List<Employee> emp = service.getAllEmp();
		m.addAttribute("emp",emp);
		return "index";
	}

	@GetMapping("/addemp")
	public String addemp(){
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e,HttpSession session) {
		service.addEmp(e);
		System.out.println(e);
		session.setAttribute("msg","Employee Added");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m) {
		Employee e = service.getEmpById(id);
		m.addAttribute("emp",e);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e,HttpSession session) {
		service.addEmp(e);
		session.setAttribute("msg", "Emp Data Updated");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.delete(id);
		return"redirect:/";
	}
}

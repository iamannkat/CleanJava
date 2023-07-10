package com.CleanJava.demo.CleanJava.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.service.AccountService;
import com.CleanJava.demo.CleanJava.service.UserValidationService;

@Controller
public class LoginController {
	//hello
    @Autowired
    private UserValidationService validService;
		
	@Autowired
	public LoginController() {
	}

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {		
		return "login_page";	
	}
		
	@PostMapping("/processLogin")
	public String processLoginInfo(@RequestParam(name = "username") String username,    
                                   @RequestParam(name = "password") String password,
                                   RedirectAttributes redirect,
                                   HttpServletRequest session,
                                   Model theModel) {
        
		System.out.print(username + password);
		if (validService.validateLoginUserName(username)) { 
			theModel.addAttribute("loginError", "Invalid username!");
			return "login_page";
		}
		
		if (validService.validateLoginPassword(password)) {
			theModel.addAttribute("loginError", "Invalid password!");
			return "login_page";
		}			
		
		session.getSession().setAttribute("username", username);
		session.getSession().setAttribute("password", password);

		return "redirect:/UserAnalysis";
		
	}
	
	
	@PostMapping("/logout")
	public String destroySession(HttpServletRequest request, Model theModel) {
		request.getSession().invalidate();
		theModel.addAttribute("loginError", "You have been logged out.");
		return "login_page";
	}

}
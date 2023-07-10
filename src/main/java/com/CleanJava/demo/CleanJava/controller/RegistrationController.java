package com.CleanJava.demo.CleanJava.controller;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.DAO.ValidUser;
import com.CleanJava.demo.CleanJava.service.AccountService;
import com.CleanJava.demo.CleanJava.service.UserValidationService;

@Controller
@RequestMapping(value = "/register", method = RequestMethod.GET)
public class RegistrationController {
	
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserValidationService validService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
    @GetMapping("/showRegistrationForm")
	public String showMyRegistrationPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new ValidUser());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") ValidUser candidateUser,
				BindingResult theBindingResult, 
				HttpServletRequest request,
				Model theModel) {
		
		String userName = candidateUser.getUsername();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors() || candidateUser == null){
			 return "registration-form";
	     }

		 System.out.println("userName: "+ userName);
		 
		 if (validService.isUserNameValid(userName)) {
        	theModel.addAttribute("crmUser", new ValidUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
		 }
		 
		 if (validService.isPassWordValid(candidateUser.getPassword(), candidateUser.getMatchingPassword())) {
        	theModel.addAttribute("crmUser", new ValidUser());
			theModel.addAttribute("registrationError", "The password fields you have entered don't match.");

			logger.warning("The password fields you have entered don't match.");
        	return "registration-form";
		 }
		 
		 if (validService.isEmailValid(candidateUser.getEmail())) {
        	theModel.addAttribute("crmUser", new ValidUser());
			theModel.addAttribute("registrationError", "The email you have entered is not valid.");

			logger.warning("The email you have entered is not valid.");
        	return "registration-form";
	     }
        
        // create user account  
		Account theNewAccount = new Account();
		theNewAccount.setUsername(candidateUser.getUsername());
		theNewAccount.setPassword(candidateUser.getPassword());
		theNewAccount.setEmail(candidateUser.getEmail());
        accountService.saveNewAccount(theNewAccount);

        logger.info("Successfully created user: " + userName);

		theModel.addAttribute("username", userName);
		theModel.addAttribute("userMessage", "Congrats, " + userName + "!");
		
		request.getSession().setAttribute("username", userName);

        return "registration_confirmation";		
	}
	
	
    @PostMapping("/deleteAccount")
	public String deleteMyAccount(Model theModel, HttpServletRequest request) {
		int userID = (int) request.getSession().getAttribute("userID");
    	accountService.deleteAccountByID(userID);
    	return "homepage";
    }
    
}


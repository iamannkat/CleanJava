package com.CleanJava.demo.CleanJava.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAOImpl;
import com.CleanJava.demo.CleanJava.entity.DAO.ValidUser;
import com.CleanJava.demo.CleanJava.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) 
class LoginControllerTests {

    @Autowired
    private MockMvc mvc;

    Account account; 
    
    @Test
    void canShowMyRegistrationPage() throws Exception {
    	mvc.perform(get("/showMyLoginPage")
      	      .accept(MediaType.APPLICATION_JSON_VALUE))
    	      .andExpect(status().isOk());
    }
    
    
    @Test
    void canProcessLoginFormWithCorrectInputs() throws Exception {
        
        // when
        mvc.perform(post("/processLogin")
                        .contentType(MediaType.APPLICATION_JSON)
  		      	        .param("username", "annakat")
  		      	        .param("password", "annaariana"))
			        .andExpect(status().isFound())
					.andExpect(redirectedUrl("/UserAnalysis"));

    }
    
    
    @Test
    void canProcessLoginFormWithInvalidUsername() throws Exception {

        mvc.perform(post("/processLogin")
                        .contentType(MediaType.APPLICATION_JSON)
  		      	        .param("username", "annakat88")
  		      	        .param("password", "annaariana"))
                .andExpect(status().isOk()); 

    }
    
    
    @Test
    void canProcessLoginFormWithInvalidPassword() throws Exception {
   
        // when
        mvc.perform(post("/processLogin")
                        .contentType(MediaType.APPLICATION_JSON)
  		      	        .param("username", "annakat")
  		      	        .param("password", "wrongPassword"))
                .andExpect(status().isOk()); 
	
    }
    
    
    @Test
    void canLogoutFromSession() throws Exception {
   
        // when
        mvc.perform(post("/logout"))
                .andExpect(status().isOk()); 
	
    }
    
}

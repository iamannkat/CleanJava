package com.CleanJava.demo.CleanJava.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAOImpl;
import com.CleanJava.demo.CleanJava.entity.DAO.ValidUser;
import com.CleanJava.demo.CleanJava.service.AccountService;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // to ignore 403 errors
class RegistrationControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private AccountDAOImpl accountRepository;
    
    @Autowired
    private AccountService accountService;
    
    Account account;

    @BeforeEach
    void setUp() {

    }
//
//    @AfterAll
//    void tearDown() {
//    	accountRepository.deleteAccountByID(account.getId());
//    }
//    
    
    @Test
    void canShowMyRegistrationPage() throws Exception {
    	mvc.perform(get("/register/showRegistrationForm")
      	      .accept(MediaType.APPLICATION_JSON_VALUE))
    	      .andExpect(status().isOk());
    }
    
    
    @Test
    void canProcessRegistrationFormWithCorrectInputs() throws Exception {
    	
		   ValidUser candidateUser = new ValidUser();
			  candidateUser.setEmail("newUser@gmail.com");
			  candidateUser.setPassword("password");
			  candidateUser.setMatchingPassword("password");
			  candidateUser.setUsername("newUser");

        // when
        mvc.perform(post("/register/processRegistrationForm")
                        .contentType(MediaType.APPLICATION_JSON)
  		      	        .flashAttr("crmUser", candidateUser))
 
                .andExpect(status().isOk());

        // then
        assertThat(accountRepository.findAccountByUserName("newUser")).isNotNull();
        
        account = accountService.findAccountByUserName("newUser");
//        account.setId(8);
        assertEquals(candidateUser.getUsername(), account.getUsername());
        assertEquals(candidateUser.getEmail(), account.getEmail());
        
    }
    
    @Test
    void canProcessRegistrationFormWithUnmatchedPassword() throws Exception {
    	
		   ValidUser candidateUser = new ValidUser();
			  candidateUser.setEmail("anotherUser@gmail.com");
			  candidateUser.setPassword("password");
			  candidateUser.setMatchingPassword("differentpassword");
			  candidateUser.setUsername("anotherUser");

	     // when
	     mvc.perform(post("/register/processRegistrationForm")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .flashAttr("crmUser", candidateUser))
	
	             .andExpect(status().isOk());
	
	     // then
	     assertThat(accountRepository.findAccountByUserName("anotherUser")).isNull();
	
    }
    
    @Test
    void canProcessRegistrationFormWithExistingUsername() throws Exception {
    
		   ValidUser candidateUser = new ValidUser();
			  candidateUser.setEmail("anotherUser@gmail.com");
			  candidateUser.setPassword("password");
			  candidateUser.setMatchingPassword("password");
			  candidateUser.setUsername("newUser");

	     // when
	     mvc.perform(post("/register/processRegistrationForm")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .flashAttr("crmUser", candidateUser))
	
	             .andExpect(status().isOk());
	
	     // then
	     assertThat(accountRepository.findAccountByUserName("newUser")).isNotNull();
	     // fix the test later
	
    }
    
    @Test
    void canProcessRegistrationFormWithinvalidEmail() throws Exception {
    
		   ValidUser candidateUser = new ValidUser();
			  candidateUser.setEmail("anotherUsermail.com");
			  candidateUser.setPassword("password");
			  candidateUser.setMatchingPassword("password");
			  candidateUser.setUsername("newUser1");

	     // when
	     mvc.perform(post("/register/processRegistrationForm")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .flashAttr("crmUser", candidateUser))
	
	             .andExpect(status().isOk());
	
	     // then
	     assertThat(accountRepository.findAccountByUserName("newUser")).isNull();
	     // fix the test later
	
    }
    
	   
	   @Test
	   public void canDeleteAccount() throws Exception {
		  
		     // when
		     mvc.perform(post("/register/deleteAccount")
		                     .contentType(MediaType.APPLICATION_JSON)
		                     .sessionAttr("userID", 2))		
				        .andExpect(status().isOk());
		     assertThat(accountRepository.findAccountByID(2)).isNull();
		
	   }
    
}
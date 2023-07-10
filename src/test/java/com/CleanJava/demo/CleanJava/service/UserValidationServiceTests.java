package com.CleanJava.demo.CleanJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAOImpl;
import com.CleanJava.demo.CleanJava.entity.DAO.SettingsDAOImpl;
import com.CleanJava.demo.CleanJava.service.AccountServiceImpl;
import com.CleanJava.demo.CleanJava.service.AnalysisServiceImpl;
import com.CleanJava.demo.CleanJava.service.UserValidationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserValidationServiceTests { 
	  
	  @Mock
	  private AccountDAOImpl accountdao; 
	  
	  @InjectMocks
	  UserValidationServiceImpl validationService;
	  
	  private Account accountTest;
	  
	  @BeforeEach
	  void initUseCase() {
		accountTest = new Account(22, "annakat", "1234", "liann@gmail.com", null); 
	  }
	  
	  
	  @Test
	  @DisplayName("canCheckIfPasswordIsValid")
	  void canCheckIfPasswordIsValid() {
		    String password = "liann2001";
		    String matchingPassword = "liann2001";
		    String notMatchingPassword = "liann2000";
		    
			boolean answerFalse = validationService.isPassWordValid(password, matchingPassword);
		    assertFalse(answerFalse);
		    
			boolean answerTrue = validationService.isPassWordValid(password, notMatchingPassword);
		    assertTrue(answerTrue);
	  }
	  
	  
	  @Test
	  @DisplayName("canCheckIfEmailIsValid")
	  void canCheckIfEmailIsValid() {
		    String validEmail = "liann@gmail.com";
		    String notValidEmail = "lianngmam";
		    
//		    Mockito.when(accountdao.findSettingsByAccountID(accountTest.getId()))
//		      .thenReturn(listTest);
	
		    
			boolean answerFalse = validationService.isEmailValid(validEmail);
		    assertFalse(answerFalse);
		    
			boolean answerTrue = validationService.isEmailValid(notValidEmail);
		    assertTrue(answerTrue);
	  }
	  
	  
	  @Test
	  @DisplayName("canCheckIfUsernameIsValid")
	  void canCheckIfUsernameIsValid() {
		    String validUsername = "annakat";
		    String notValidUsername = "liann";
		    
			Account accountTest = new Account(22, "annakat", "1234", "liann@gmail.com", null); 
		    
		    Mockito.when(accountdao.findAccountByUserName(validUsername))
		      .thenReturn(accountTest);
		    
			boolean answerFalse = validationService.isUserNameValid(validUsername);
		    assertTrue(answerFalse);

			boolean answerTrue = validationService.isUserNameValid(notValidUsername);
		    assertFalse(answerTrue);

	  }
	  
	  
	  @Test
	  @DisplayName("Validate Login with valid username")
	  void canValidateLoginUserValidUserName() {
		    String existingUsername = "annakat";
//		    String nonExistingUsername = "annakat";
		    
			Account accountTest = new Account(22, "annakat", "1234", "liann@gmail.com", null); 
		    
		    Mockito.when(accountdao.findAccountByUserName(existingUsername))
		      .thenReturn(accountTest);
		    
			boolean answerFalse = validationService.validateLoginUserName(existingUsername);
		    assertFalse(answerFalse);
		    
//			boolean answerTrue = validationService.validateLoginUserName(notValidEUsername);
//		    assertTrue(answerTrue);
	  }
	  
	  @Test
	  @DisplayName("Validate Login with invalid username")
	  void canValidateLoginWithInvalidUserName() {
		    String existingUsername = "liann";
		    String nonExistingUsername = "liann";
		    
			Account accountTest = new Account(22, "annakat", "1234", "liann@gmail.com", null); 
		    
//		    Mockito.when(accountdao.findAccountByUserName(existingUsername))
//		      .thenReturn(accountTest);
		    
//			boolean answerFalse = validationService.validateLoginUserName(existingUsername);
//		    assertFalse(answerFalse);
		    
			boolean answerTrue = validationService.validateLoginUserName(nonExistingUsername);
		    assertTrue(answerTrue);
	  }
	  
	  @Test
	  @DisplayName("Validate Login with valid password")
	  void canValidateLoginWithValidPassword() {
		    String validPassword = "liann2001";
		    
			Account accountTest = new Account(22, "annakat", "liann2001", "liann@gmail.com", null); 
			validationService.setUserAccount(accountTest);

			boolean answerFalse = validationService.validateLoginPassword(validPassword);
		    assertFalse(answerFalse);

	  }
	  
	  @Test
	  @DisplayName("Validate Login with invalid password")
	  void canValidateLoginWithInvalidPassword() {
		    String nonValidPassword = "liann345";
		    
			Account accountTest = new Account(22, "annakat", "1234", "liann@gmail.com", null); 
			validationService.setUserAccount(accountTest);
//		    Mockito.when(accountdao.findAccountByUserName(existingUsername))
//		      .thenReturn(accountTest);
		    
			boolean answerTrue = validationService.validateLoginPassword(nonValidPassword);
		    assertTrue(answerTrue);
	  }

}

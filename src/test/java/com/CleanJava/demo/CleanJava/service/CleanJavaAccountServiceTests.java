package com.CleanJava.demo.CleanJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAO;
import com.CleanJava.demo.CleanJava.entity.DAO.SettingsDAO;
import com.CleanJava.demo.CleanJava.service.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CleanJavaAccountServiceTests {

	  @Mock
	  private AccountDAO accountdao; 
	  
	  @Mock
	  private SettingsDAO settingdao; 
 
	  @InjectMocks
	  AccountServiceImpl service;
	  
	  Account accountTest;
	  
	  Setting settingTest;
	  
	  
	  @BeforeEach
	  void initUseCase() {
	    accountTest = new Account(22, "annakat", "1234", "iamannkat@gmail.com", null);
	    settingTest = new Setting(6, accountTest, 67, 45, 5, 22, 399);

	  }
	  
	  @Test
	  @DisplayName("canFindAllAccounts")
	  void canFindAllAccounts() {
	      Account accountTestOne = new Account(22, "annakat", "1234", "iamannkat@gmail.com", null);
	      Account accountTestTwo = new Account(22, "annakat", "1234", "iamannkat@gmail.com", null);

		  List<Account> allAccounts = new ArrayList<Account>();
		  allAccounts.add(accountTestOne);
		  allAccounts.add(accountTestTwo);

	      when(accountdao.findAllAccounts())
	         .thenReturn(allAccounts);
	    
		 List<Account> returnedAccounts = service.findAllAccounts();
		  assertThat(returnedAccounts).isNotNull();
			  

	  }
	  
	  @Test
	  @DisplayName("canFindAccountById")
	  void checkFindAccountById() {		   		  
			  
	      when(accountdao.findAccountByID(accountTest.getId()))
	         .thenReturn(accountTest);
		    
		  Account savedAccount = service.findAccountByID(22);
		  assertEquals(savedAccount.getUsername(), accountTest.getUsername());

	  }
	  
	  @Test
	  @DisplayName("canFindAccountByUsername")
	  void canFindAccountByUsername() {
	      when(accountdao.findAccountByUserName(accountTest.getUsername()))
	         .thenReturn(accountTest);
	    
		  Account savedAccount = service.findAccountByUserName("annakat");
		  assertEquals(savedAccount.getId(), accountTest.getId());
	  }
	  
	  @Test
	  @DisplayName("canRegisterNewUser")
	  void canRegisterNewUser() {
		  
		  Mockito.when(accountdao.saveNewAccount(accountTest))
	      .thenReturn(accountTest); 
		    
		  Account savedAccount = service.saveNewAccount(accountTest);
		  assertThat(savedAccount).isNotNull();
		  assertEquals(savedAccount.getId(), accountTest.getId());

	  }
	  
	  @Test
	  @DisplayName("canDeleteAccount")
	  void canDeleteAccount() {
		  
		 Mockito.when(accountdao.deleteAccountByID(accountTest.getId()))
		      .thenReturn(accountTest.getId());
		 
		  int deletedAccountId = service.deleteAccountByID(22);
		  assertEquals(deletedAccountId, accountTest.getId());

	  }
}


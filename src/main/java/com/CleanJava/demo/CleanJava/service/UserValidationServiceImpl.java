
package com.CleanJava.demo.CleanJava.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAO;
import com.CleanJava.demo.CleanJava.entity.DAO.ValidUser;

@Service
public class UserValidationServiceImpl implements UserValidationService {
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final AccountDAO accountDAO;
	private Account userAccount;


	@Autowired
	public UserValidationServiceImpl(AccountDAO theAccountDAO) {
		accountDAO = theAccountDAO;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public boolean isPassWordValid(String password, String matchingPassword) {
		
		if (!(password.matches(matchingPassword))) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isEmailValid(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		if(!(matcher.matches())) {
			return true;//if it doest matck = true
		}
        return false;
	}
	
	
	public boolean isUserNameValid(String theUserName) {
        Account existing = accountDAO.findAccountByUserName(theUserName);
        if (existing != null){
        	return true; //if it does exist
        }
        return false;
	}

	@Override
	public boolean validateLoginUserName(String candidateUsername) {
		this.userAccount = accountDAO.findAccountByUserName(candidateUsername);
		if (candidateUsername == null || userAccount == null) {
			return true; // no account exists
		}
		return false;
	}

	@Override
	public boolean validateLoginPassword(String candidatePassword) {
		String password = this.userAccount.getPassword();
		
		if (candidatePassword == null) {
			return true;
			
		} else if (!(candidatePassword.equals(password))) {
			return true;
		}
		return false;
	}

}

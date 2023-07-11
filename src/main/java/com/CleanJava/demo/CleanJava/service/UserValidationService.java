package com.CleanJava.demo.CleanJava.service;

public interface UserValidationService {
	
	public boolean validateLoginUserName(String candidateUsername);
	
	public boolean validateLoginPassword(String candidatePassword);
	
	public boolean isPassWordValid(String password, String matchingPassword);
	
	public boolean isEmailValid(String email);
	
	public boolean isUserNameValid(String username);
}

package com.CleanJava.demo.CleanJava.entity.DAO;

import java.util.List;

import com.CleanJava.demo.CleanJava.entity.Account;

public interface AccountDAO {

	public List<Account> findAllAccounts();
	
	public Account findAccountByID(int id);
	
	public Account saveNewAccount(Account theAccount);
	
	public int deleteAccountByID(int id);
		
	public Account findAccountByUserName(String theUserName);
}

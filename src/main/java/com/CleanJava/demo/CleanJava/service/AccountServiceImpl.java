package com.CleanJava.demo.CleanJava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAO;
import com.CleanJava.demo.CleanJava.entity.DAO.SettingsDAO;


@Service
//@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountDAO accountDAO;
	
	@Autowired
	public AccountServiceImpl(AccountDAO theAccountDAO) {
		accountDAO = theAccountDAO;
	}
	
	@Override
	@Transactional
	public List<Account> findAllAccounts() {
		return accountDAO.findAllAccounts();
	}

	@Override
	@Transactional
	public Account findAccountByID(int id) {
		return accountDAO.findAccountByID(id);
	}
	

	@Override
	@Transactional
	public Account saveNewAccount(Account theNewAccount) {
		return accountDAO.saveNewAccount(theNewAccount);
	}

	@Override
	@Transactional
	public int deleteAccountByID(int id) {
		return accountDAO.deleteAccountByID(id);		
	}
	
	@Override
	@Transactional
	public Account findAccountByUserName(String theUserName) {
		return accountDAO.findAccountByUserName(theUserName);
	}

}

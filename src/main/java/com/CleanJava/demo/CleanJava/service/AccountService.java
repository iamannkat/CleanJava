package com.CleanJava.demo.CleanJava.service;

import java.util.List;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;

public interface AccountService {

	public List<Account> findAllAccounts();
	
	public Account findAccountByID(int id);

	public Account saveNewAccount(Account theAccount);
	
	public int deleteAccountByID(int id);	
	
	public Account findAccountByUserName(String theUserName);	

//	
//	public Setting saveNewSetting(Setting theSetting); 
//	
//	public int deleteSettingByID(int settingId);
//	
//	public void updateSetting(Setting newSetting);
//		
//	public List<Setting> findSettingsByAccountID(int accountId);	
//	
//	public Setting findSettingBySettingID(int settingId);
	
}

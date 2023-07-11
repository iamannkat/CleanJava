package com.CleanJava.demo.CleanJava.service;

import java.util.List;

import com.CleanJava.demo.CleanJava.entity.Setting;

public interface AnalysisService {
	
	public Setting saveNewSetting(Setting theSetting); 
	
	public int deleteSettingByID(int settingId);
	
	public void updateSetting(Setting newSetting);
		
	public List<Setting> findSettingsByAccountID(int accountId);	
	
	public Setting findSettingBySettingID(int settingId);
}

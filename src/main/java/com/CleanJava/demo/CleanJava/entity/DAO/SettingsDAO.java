package com.CleanJava.demo.CleanJava.entity.DAO;

import java.util.List;

import com.CleanJava.demo.CleanJava.entity.Setting;

public interface SettingsDAO {

	public List<Setting> findSettingsByAccountID(int accountId);
	
	public Setting saveNewSetting(Setting theSetting); 

	public Setting updateSetting(Setting newSetting);
	
	public int deleteSettingByID(int settingId);
	
	public Setting findSettingBySettingID(int settingId);
	
}

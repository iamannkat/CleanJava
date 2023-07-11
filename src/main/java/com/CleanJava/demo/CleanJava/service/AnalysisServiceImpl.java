package com.CleanJava.demo.CleanJava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.entity.DAO.SettingsDAO;

@Service
public class AnalysisServiceImpl implements AnalysisService{
	
	private final SettingsDAO settingsDAO;
	
	@Autowired
	public AnalysisServiceImpl(SettingsDAO theSettingsDAO) {
		settingsDAO = theSettingsDAO;
	}
	
	@Override
	@Transactional
	public Setting saveNewSetting(Setting theSetting) {
		return settingsDAO.saveNewSetting(theSetting);
		
	}

	@Override
	@Transactional
	public int deleteSettingByID(int settingId) {
		return settingsDAO.deleteSettingByID(settingId);
		
	}

	@Override
	@Transactional
	public void updateSetting(Setting newSetting) {
		settingsDAO.updateSetting(newSetting);
	}
	
	@Override
	@Transactional
	public List<Setting> findSettingsByAccountID(int accountId) {
		return settingsDAO.findSettingsByAccountID(accountId);
	}


	@Override
	public Setting findSettingBySettingID(int settingId) {
		return settingsDAO.findSettingBySettingID(settingId);
	}
}

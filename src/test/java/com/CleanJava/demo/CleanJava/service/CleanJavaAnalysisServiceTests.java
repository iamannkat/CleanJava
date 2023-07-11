package com.CleanJava.demo.CleanJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.CleanJava.demo.CleanJava.entity.DAO.AccountDAOImpl;
import com.CleanJava.demo.CleanJava.entity.DAO.SettingsDAOImpl;
import com.CleanJava.demo.CleanJava.service.AccountServiceImpl;
import com.CleanJava.demo.CleanJava.service.AnalysisServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CleanJavaAnalysisServiceTests {
	  
	  @Mock
	  private SettingsDAOImpl settingdao; 
	  
	  @InjectMocks
	  AnalysisServiceImpl analysisService;
	  
	  @Mock
	  private AccountDAOImpl accountdao; 
	  
	  @InjectMocks
	  AccountServiceImpl accountService;
	  
	  Account accountTest;
	  Setting settingSave;
	  Setting settingDelete;


	  @BeforeEach
	  void initUseCase() {
		  
		accountTest = new Account(22, "annakat", "1234", "iamannkat@gmail.com", null); 
	    settingSave = new Setting(6, accountTest, 67, 45, 5, 22, 399);
	    settingDelete = new Setting(8, accountTest, 67, 45, 5, 22, 399);
		    
	  }
	  
	  @Test
	  @DisplayName("canFindSettingsByAccountId")
	  void canFindSettingsByAccountId() {
		    Setting settingOne = new Setting(5, accountTest, 67, 45, 5, 22, 399);
		    Setting settingTwo = new Setting(8, accountTest, 80, 50, 8, 34, 600);
		    List<Setting> listTest = new ArrayList<Setting>();
		    listTest.add(settingOne);
		    listTest.add(settingTwo);
		    
		    Mockito.when(settingdao.findSettingsByAccountID(accountTest.getId()))
		      .thenReturn(listTest);
		    
			List<Setting> savedSettings = analysisService.findSettingsByAccountID(accountTest.getId());
		    assertThat(savedSettings).isNotNull();

	  }
	  
	  @Test
	  @DisplayName("canFindSettingBySettingId")
	  void canFindSettingBySettingId() {
		    
		    Mockito.when(settingdao.findSettingBySettingID(settingSave.getSetting_id()))
		      .thenReturn(settingSave);
		    
		    Setting savedSetting = analysisService.findSettingBySettingID(settingSave.getSetting_id());
	        assertThat(savedSetting.getSetting_id()).isNotNull();
	        assertEquals(savedSetting.getSetting_id(), settingSave.getSetting_id());

	  }
	  
	  @Test
	  @DisplayName("canSaveNewSetting")
	  void canSaveNewSetting() {
		  
		    Mockito.when(settingdao.saveNewSetting(settingSave))
		      .thenReturn(settingSave);
		    
		    Setting savedSetting = analysisService.saveNewSetting(settingSave);		   
		    assertThat(savedSetting).isNotNull();

	  }
	  
	  @Test
	  @DisplayName("canDeleteSetting")
	  void canDeleteSetting() {

		    Mockito.when(settingdao.deleteSettingByID(settingDelete.getSetting_id()))
		      .thenReturn(settingDelete.getSetting_id());
		    
		    int deletedSettingId = analysisService.deleteSettingByID(settingDelete.getSetting_id());
	        assertThat(deletedSettingId).isNotNull();
	        assertEquals(deletedSettingId, settingDelete.getSetting_id());

	  }

}


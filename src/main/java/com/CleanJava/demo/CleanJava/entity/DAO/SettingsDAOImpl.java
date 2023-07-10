package com.CleanJava.demo.CleanJava.entity.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;

@Repository
public class SettingsDAOImpl implements SettingsDAO {
	
	@Autowired
	private EntityManager entityManager;
		
	@Autowired
	public SettingsDAOImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	public List<Setting> findSettingsByAccountID(int accountId) { 
		Session currentSession = entityManager.unwrap(Session.class);
		Account theAccount= currentSession.get(Account.class, accountId); 		
		return theAccount.getSettings();
	}

	@Override
	public Setting saveNewSetting(Setting theSetting) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theSetting);
		return theSetting;
		
	}

	@Override
	public int deleteSettingByID(int settingId) {
		System.out.println(settingId);
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Setting where setting_id=:settingId")

		.setParameter("settingId", settingId);
		theQuery.executeUpdate();
		return settingId; 
		
	}

	@Override
	public Setting updateSetting(Setting updatedSetting) {
		Session currentSession = entityManager.unwrap(Session.class);
	
//		int accountID = updatedSetting.getAccount().getId();
//		int settingID = updatedSetting.getSetting_id();
		//Query theQuery = currentSession.createQuery("select from settings where setting_id=:settingID");
		
//		Setting theSetting = currentSession.get(Setting.class, settingID); 		

		currentSession.saveOrUpdate(updatedSetting);
		return updatedSetting;
	}

	@Override
	public Setting findSettingBySettingID(int settingId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Setting theSetting = currentSession.get(Setting.class, settingId); 	
		return theSetting;
	}


}

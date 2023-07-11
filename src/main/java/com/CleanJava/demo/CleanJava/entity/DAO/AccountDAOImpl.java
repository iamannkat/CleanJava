package com.CleanJava.demo.CleanJava.entity.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.CleanJava.demo.CleanJava.entity.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private EntityManager entityManager;
		
	@Autowired
	public AccountDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Account> findAllAccounts() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Account> theQuery =
				currentSession.createQuery("from Account", Account.class);
		List<Account> accounts = theQuery.getResultList();
		return accounts;
	}


	@Override
	public Account findAccountByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Account theAccount = currentSession.get(Account.class, id);
		
		return theAccount;
	}

	@Override
	public Account findAccountByUserName(String username) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
//		Account theAccount = currentSession.get(Account.class, username);

		Query<Account> theQuery = currentSession.createQuery("from Account where username=:username", Account.class);
		theQuery.setParameter("username", username);
		Account theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public Account saveNewAccount(Account theAccount) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theAccount);
		return theAccount;
	}

	@Override
	public int deleteAccountByID(int id) {
		Session currentSession = entityManager.unwrap(Session.class);	
		Query theQuery2 = currentSession.createQuery("delete from Account where id = : id");
		theQuery2.setParameter("id", id);
		theQuery2.executeUpdate();
		
		return id;
	}

}








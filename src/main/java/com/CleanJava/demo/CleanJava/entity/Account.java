package com.CleanJava.demo.CleanJava.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@Entity 
@Table(name = "account")  // instructor bi directional
public class Account { // an instructor can have many courses
	                   // An account can have many settings
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "account" , cascade = CascadeType.ALL, orphanRemoval = true)
//			   cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//					      CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}) 
	private List<Setting> settings;
	//CascadeType.PERSIST CascadeType.MERGE
	public Account(int id, String username, String password, String email, List<Setting> settings) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.settings = settings;
	}
	
	public Account() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	// convience method for bi directional relationship
	public void add(Setting newSetting) {
		if (settings == null) {
			settings = new ArrayList<Setting>();
		}
		
		settings.add(newSetting);
		newSetting.setAccount(this); 
	}
	
}

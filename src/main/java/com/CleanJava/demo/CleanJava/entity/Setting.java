package com.CleanJava.demo.CleanJava.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor

@Entity
@Table(name = "setting") 
public class Setting{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "setting_id")
	private int setting_id;

	// foreign key from account table
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,
			              CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name = "account")
	private Account account; 	
	
	@Column(name = "constructor_length")
	private int constructorlength;

	@Column(name = "method_length")
	private int methodlength;
	
	@Column(name = "number_of_parameters")
	private int numberOfParameters;
	
	@Column(name = "characters_per_line")
	private int charactersPerLine;
	
	@Column(name = "lines_per_file")
	private int linesPerFile;
	
	public Setting(int setting_id, Account account, int constructorlength, int methodlength, int numberOfParameters,
			int charactersPerLine, int linesPerFile) {
		super();
		this.setting_id = setting_id;
		this.account = account;
		this.constructorlength = constructorlength;
		this.methodlength = methodlength;
		this.numberOfParameters = numberOfParameters;
		this.charactersPerLine = charactersPerLine;
		this.linesPerFile = linesPerFile;
	}

	public Setting() {
		super();
		this.constructorlength = 20;
		this.methodlength = 150;
		this.numberOfParameters = 4;
		this.charactersPerLine = 80;
		this.linesPerFile = 2000;
	}

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public int getSetting_id() {
		return setting_id;
	}

	public void setSetting_id(int setting_id) {
		this.setting_id = setting_id;
	}

	public int getConstructorlength() {
		return constructorlength;
	}

	public void setConstructorlength(int constructorlength) {
		this.constructorlength = constructorlength;
	}

	
	public int getMethodlength() {
		return methodlength;
	}

	public void setMethodlength(int methodlength) {
		this.methodlength = methodlength;
	}
	
	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	public void setNumberOfParameters(int numberOfParameters) {
		this.numberOfParameters = numberOfParameters;
	}

	public int getCharactersPerLine() {
		return charactersPerLine;
	}

	public void setCharactersPerLine(int charactersPerLine) {
		this.charactersPerLine = charactersPerLine;
	}

	public int getLinesPerFile() {
		return linesPerFile;
	}

	public void setLinesPerFile(int linesPerFile) {
		this.linesPerFile = linesPerFile;
	}
	
	@Override
	public String toString() {
		return "Setting [setting_id=" + setting_id + ", account=" + account + ", constructorlength=" + constructorlength
				+ ", methodlength=" + methodlength + ", numberOfParameters=" + numberOfParameters
				+ ", charactersPerLine=" + charactersPerLine + ", linesPerFile=" + linesPerFile + "]";
	}

}

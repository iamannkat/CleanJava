package com.CleanJava.demo.CleanJava.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.CleanJava.demo.CleanJava.entity.Setting;

public class WriteResultsInFileCommand implements Command {
	
	private String SettingUsedAtLastCheck;
	private List<String> conventionErrors;
	private List<String> metrics;
	private String username;
	private String sourceFileName;	

	public WriteResultsInFileCommand(String settingUsedAtLastCheck, List<String> conventionErrors, 
			List<String> metrics,String username, String sourceFileName) {
		super();
		this.SettingUsedAtLastCheck = settingUsedAtLastCheck;
		this.conventionErrors = conventionErrors;
		this.metrics = metrics;
		this.username = username;
		this.sourceFileName = sourceFileName;
	}
	
	public WriteResultsInFileCommand() {
		super();
		SettingUsedAtLastCheck = "88";
		this.conventionErrors = new ArrayList<String>();
		this.metrics = new ArrayList<String>();
		this.username = "annakat";
		this.sourceFileName = "sample_file";
	}

	@Override
	public void execute() throws IOException, FileNotFoundException {
		String homePath = System.getProperty("user.home") + "/Downloads/" + "CleanJava_" + sourceFileName + ".txt";
		System.out.println("homePath : " + homePath);

		 try {
		      File myDownload = new File(homePath);
		      
		      FileWriter myWriter = new FileWriter(homePath.replace(".java", ""));
		      myWriter.write("CleanJava \n");
		      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		      LocalDateTime now = LocalDateTime.now(); 
		      myWriter.write(dtf.format(now) + "\n");
		      myWriter.write("User: " + username + "\n");

		      myWriter.write("Analysis results for source file with name " + sourceFileName + "\n");
		      myWriter.write("================================================" + "\n");

		      String[] temp = SettingUsedAtLastCheck.split(",");
		      myWriter.write("Checked with " + temp[0].replace("Setting [", "").replace("]", "") + "\n");

		      for (int i = 5; i < temp.length; i ++) {
			      myWriter.write(temp[i].replace("]", "") + "\n");
		      }
//		      myWriter.write("Checked with setting Id: " + SettingUsedAtLastCheck.getSetting_id() + "\n" +
//		      "Characters Per Line: " + SettingUsedAtLastCheck.getCharactersPerLine() + "\n" +
//		      "Constructor Length: " + SettingUsedAtLastCheck.getConstructorlength() + "\n" +
//		      "Lines Per File: " + SettingUsedAtLastCheck.getLinesPerFile() + "\n" +
//		      "Method Length: " + SettingUsedAtLastCheck.getMethodlength() + "\n" +
//		      "Number Of Parameters: " + SettingUsedAtLastCheck.getNumberOfParameters() + "\n");
		      myWriter.write("=================================================" + "\n");

		      for(String error: conventionErrors) {
		    	  myWriter.write(error + "\n\n");
		      }
		      
		      myWriter.write("=================================================" + "\n");
		      myWriter.write("The Metrics\n");

		      myWriter.write("Boolean Expression Complexity: " + metrics.get(0) + "\n");
			  myWriter.write("Class Complexity: " + metrics.get(1) + "\n");  
			  myWriter.write("LCOM: " + metrics.get(3) + "\n");
			  myWriter.write("Cyclomatic Complexity: " + metrics.get(2) + "\n"); 		 
	
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating and writing the file to be downloaded.");
		      e.printStackTrace();
		    }


	}

}

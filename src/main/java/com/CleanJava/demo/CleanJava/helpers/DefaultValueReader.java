package com.CleanJava.demo.CleanJava.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultValueReader {
	
	private static final String filename = "C:\\Users\\Anna\\Desktop\\CleanJava\\"
			+ "src\\main\\java\\com\\CleanJava\\demo\\CleanJava\\helpers\\default_conventions";
	
	private Path path;
	private static DefaultValueReader instance;	
	private File file;   
	private FileReader fr;  
	private BufferedReader br;
	
	public int LINES_PER_FILE;
	public int LINE_LENGTH;
	public int METHOD_LENGTH;
	public int CONSTRUCTOR_LENGTH;
	public int DECLARATIONS_PER_LINE;
	public int PARAMETER_NUMBER;
	public int NUMBER_OF_METHODS;
	public int NUMBER_OF_FORS;
	public int NUMBER_OF_FIELDS;

	public DefaultValueReader()  {
		
//		String filename = Paths.get("ConstructorLengthChecker.java").toFile().getAbsolutePath();
//		System.out.println(filename);

		try  
		{  
			file=new File(filename);  
			fr=new FileReader(file);  
			br=new BufferedReader(fr); 			
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		} 
		read();
	}  
	
	
	public void read() {
		try  
		{  
			String line;  
		while((line=br.readLine())!=null)  
		{  
			if (line.contains("LINES_PER_FILE")) {
				this.LINES_PER_FILE = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("LINE_LENGTH")) {
				this.LINE_LENGTH = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("METHOD_LENGTH")) {
				this.METHOD_LENGTH = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("CONSTRUCTOR_LENGTH")) {
				this.CONSTRUCTOR_LENGTH = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("DECLARATIONS_PER_LINE")) {
				this.DECLARATIONS_PER_LINE = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("PARAMETER_NUMBER")) {
				this.PARAMETER_NUMBER = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("NUMBER_OF_METHODS")) {
				this.NUMBER_OF_METHODS = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("NUMBER_OF_FORS")) {
				this.NUMBER_OF_FORS = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			else if (line.contains("NUMBER_OF_FIELDS")) {
				this.NUMBER_OF_FIELDS = Integer.parseInt(line.replaceAll("[^0-9]", " ").trim());	                 
			}
			 
		}  
			fr.close();  
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		} 
	}
	
	public static DefaultValueReader getInstance() {
        if (instance == null) {
            instance = new DefaultValueReader();
        }
        return instance;
     }

}

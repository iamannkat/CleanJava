package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class CharactersPerLineCheckers implements IConventionChecker{
	
	private ParserFacade parser;
	private int max_characters_per_line;
	private ErrorCodeCollector errorCodeCollector;
	
	public CharactersPerLineCheckers(int max_characters_per_line) throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();
	    this.max_characters_per_line = max_characters_per_line;
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}
	
	@Override
	public void check() throws IOException  {
		  int numberOfLines = parser.getLinesPerFile();
		  
		  for (int lineNumber=0; lineNumber < numberOfLines; lineNumber++) {
			  
			  int lineLength = parser.getLineLength(lineNumber);
			  
			  if (lineLength > max_characters_per_line) {
				  errorCodeCollector.addErrorCode("TooManyCharactersInOneLine",
						    Integer.toString(lineLength),
							Integer.toString(lineNumber + 1));	
				  
				  errorCodeCollector.addErrorMessage("Convention error at line: " 
		            + Integer.toString(lineNumber + 1)
			        + "\nThis line is too long at "+ Integer.toString(lineLength) 
			        + " characters. "); 
				
			  } 
		  }

	}
}

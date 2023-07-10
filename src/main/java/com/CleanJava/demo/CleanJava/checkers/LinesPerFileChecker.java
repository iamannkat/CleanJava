package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class LinesPerFileChecker implements IConventionChecker {
	
	private static ParserFacade parser;
	private int max_lines;
	private int lines_per_file;
	private ErrorCodeCollector errorCodeCollector;

	public LinesPerFileChecker(int max_lines) throws FileNotFoundException {
		parser = ParserFacade.getInstance();
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
		this.max_lines = max_lines;
	}

	@Override
	public void check() throws IOException {

		this.lines_per_file = parser.getLinesPerFile();
		
		if (lines_per_file > max_lines) {
      	    errorCodeCollector.addErrorCode("TooLengthyFile",
      	    		          Integer.toString(lines_per_file),
      	    		          Integer.toString(max_lines));
      	    
      	    
      	  errorCodeCollector.addErrorMessage("Convention Error: The number of lines of your "
			+ "source file is " + Integer.toString(lines_per_file) 
			+ ".\n The limit is " + Integer.toString(max_lines)
			+ ". consider breaking up your class into two seperate classes.");
		}
	}

}

package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;


public class MethodLengthChecker implements IConventionChecker {
	
	private ParserFacade parser;
	private int max_method_length;
	private ErrorCodeCollector errorCodeCollector;
	
	public MethodLengthChecker(int max_method_length) throws FileNotFoundException {;
		this.parser = ParserFacade.getInstance();
		this.max_method_length = max_method_length;
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}

	@Override
	public void check() throws IOException {
		
		List<String> methodBlocks = parser.getMethodBlocks();

	    for (int i=0; i < methodBlocks.size(); i += 3) {
	    	
	          Stream<String> lines = methodBlocks.get(i).lines();	
	          
	          int lineCount = (int) lines.count() - 2;	
	          
	          if (lineCount > max_method_length) {
	        	  errorCodeCollector.addErrorCode("TooLengthyMethod",
	        			   methodBlocks.get(i+1) + "] is too lengthy with " 
	        	           + Integer.toString(lineCount) + " lines.", 
	        			   methodBlocks.get(i+2));
	        	  
	        	  errorCodeCollector.addErrorMessage("Convention Error at line: "
		            + methodBlocks.get(i+2) 
		            + "\nThe method with name [" + methodBlocks.get(i+1)
	        	    + "] is too lengthy with " + Integer.toString(lineCount)
	        	    + " lines.") ;
	        	  
	          }
      }
	    
	}
}

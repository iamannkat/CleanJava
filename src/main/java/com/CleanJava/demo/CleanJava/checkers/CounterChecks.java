package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.CleanJava.demo.CleanJava.helpers.DefaultValueReader;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;


public class CounterChecks implements IConventionChecker{
	
	private ParserFacade parser;
	private int number_of_methods;
	private int number_of_fields;
	private int number_of_fors;
	private DefaultValueReader defaultvalue;
	private ErrorCodeCollector errorCodeCollector;

	public CounterChecks() throws FileNotFoundException {
		 this.parser = ParserFacade.getInstance();
	     this.defaultvalue  = DefaultValueReader.getInstance();
		 this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}
	
	@Override
	public void check() throws IOException  {
		
		  //  check the number of methods
		  this.number_of_methods = parser.getJustBlock().size();
		  if (number_of_methods > defaultvalue.NUMBER_OF_METHODS) {
			 errorCodeCollector.addErrorCode("TooManyMethodDeclarationsInAClass",
					 Integer.toString(number_of_fields), 
					 Integer.toString(defaultvalue.NUMBER_OF_METHODS));
			 
			 errorCodeCollector.addErrorMessage("Convention error: There are too many methods"
		  		+ " in the source code. The number of methods is " 
				+ Integer.toString(number_of_fields) + ".\n There should be"
				+ " no more than " + Integer.toString(defaultvalue.NUMBER_OF_METHODS) + " methods. ");
          }
		  
		  //  check the number of for statements
		  this.number_of_fors = parser.getForStatements().size();
		  if (number_of_fors > defaultvalue.NUMBER_OF_FORS) {
			  errorCodeCollector.addErrorCode("TooManyForLoopsInAClass", 
					  Integer.toString(number_of_fors), 
					  Integer.toString(defaultvalue.NUMBER_OF_FORS));
			  
			  errorCodeCollector.addErrorMessage("Convention error: There are too "
				+ "many [for] statements in the source"
		        + " The number of [for] statements is " 
				+ Integer.toString(number_of_fors) + ".\n There should be"
				+ " no more than " + Integer.toString(defaultvalue.NUMBER_OF_FORS) + ".");
		  }
		  
	      //  check the number of fields 
		  this.number_of_fields = parser.getFields().size();
		  if (number_of_fields > defaultvalue.NUMBER_OF_FIELDS) {
			  errorCodeCollector.addErrorCode("TooManyFieldDeclarationsInAClass", 
					  Integer.toString(number_of_fields), 
					  Integer.toString(defaultvalue.NUMBER_OF_FIELDS));
			  
			  errorCodeCollector.addErrorMessage("Convention error: There are too many "
				+ "fields in the source. The number of fields is " 
				+ Integer.toString(number_of_fields) + ".\n There should be"
				+ " no more than " + Integer.toString(defaultvalue.NUMBER_OF_FIELDS) + ". ");
          }	
		  
			// check if there is more than one class in the java file.
		  List<String> classExpression = parser.getClassExp();

		  if (classExpression.size() > 2) {
			    errorCodeCollector.addErrorCode("MoreThanOneClassDeclarationInAFile", 
					  Integer.toString(classExpression.size()/2), "");
			    
			    errorCodeCollector.addErrorMessage("Convention error: " 
				+ "\nYour file contains " + Integer.toString(classExpression.size()/2) 
				+ " class declarations."
				+ "A java file should only contain one \nclass "
				+ "declaration per java file. ");
	      }	  
	}
}

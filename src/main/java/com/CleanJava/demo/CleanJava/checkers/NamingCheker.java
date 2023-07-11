package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.CleanJava.demo.CleanJava.helpers.DictionaryRestApi;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

import edu.mit.jwi.item.IndexWord;

public class NamingCheker implements IConventionChecker {
	
	private ParserFacade parser;
	private DictionaryRestApi dictionary;
	private ErrorCodeCollector errorCodeCollector;
	
	private final String PascalCasePattern = "^[A-Z][a-z]*([A-Z][a-z]*)";
	private final String methodCaseFormat = "^[a-z][a-zA-Z0-9]*$"; 
	private final String packageCaseFormat = "[a-z]+";
	private final String methodParameterCaseFormat = "^[a-z][_a-zA-Z0-9]+$";
	private final String constantFormat = "^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$";
	private final String localVariableFormat = "^[a-z][a-zA-Z0-9]*$";  
	
	public NamingCheker() throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();
		this.dictionary =  new DictionaryRestApi();
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}
	
	@Override
	public void check() throws IOException {
		
		List<String> names = new ArrayList<>();
		
		// check interface name format
		names = parser.getInterfaceName();	
		for(int i=0; i<names.size(); i+=2) {
			
			if(names.get(i).equals("")) {
				break;
			}
			
			String tempName = names.get(i).replace("I", "");

			if (!(tempName.matches(PascalCasePattern))) {
				errorCodeCollector.addErrorCode("InterfaceNameFormat",
						names.get(i).replace("[" ,"").replace("]", ""), 
						names.get(i+1));
				
				errorCodeCollector.addErrorMessage("Naming convention error at line: " 
				           + names.get(i+1) + "\nThe Interface with name with name [" 
						   + names.get(i).replace("[" ,"").replace("]", "")
						   + "] should \nfollow a Pascal Case Pattern. For example, TaxFilingApplication. ");
				
			}
		}
		
		
		// check package names
		names.clear();
		names = parser.getPackageNames();

		String temp[] = names.get(0).split("\\.");
		String packageName = temp[temp.length - 1];

		if(!(packageName.matches(packageCaseFormat))) {
			errorCodeCollector.addErrorCode("PascalCasePattern",
					names.get(0), names.get(1));
			
			errorCodeCollector.addErrorMessage("Naming convention error at line: " 
            + names.get(1) +
			"\nThe package with name with name [" 
			+ names.get(0) + "]\nshould " 
			+ "be in all lowercase letters. For example, com.CleanJava.demo. ");

		}
 
		 
		// check local variable names
		names.clear();
		names = parser.getVariables();

		for(int i=0; i<names.size(); i+=3) {
			
			if(names.get(i).length() > 1) {				
				if(!(names.get(i).matches(localVariableFormat))) {
					errorCodeCollector.addErrorCode("LocalVariableFormat", 
							names.get(i), names.get(i+2));
					
					errorCodeCollector.addErrorMessage("Naming convention error at line: " 
		            +  names.get(i+2) +
					"\nThe variable with name [" + names.get(i) + "] \nshould "
					+ "follow a camel Case Pattern. For example, taxFilingApplication. ");
		

				}else if(names.get(i).equals("temp")){ 
					errorCodeCollector.addErrorCode("LocalVariableNameIsTemp", 
							names.get(i), names.get(i+2));
					
					errorCodeCollector.addErrorMessage("Naming convention error at line: "
		            + names.get(i+2)
					+ "\nAvoid using names such as [temp] for your variables. \n"
					+ "Try using more descriptive names in your code. ");
				}
				
			} else {
				errorCodeCollector.addErrorCode("LocalVariableIsTooShort", 
						names.get(i), names.get(i+2));
				
				errorCodeCollector.addErrorMessage("Naming convention error at line: "
	             + names.get(i+2) + "\nThe local variable with name [" 
	             + names.get(i) + "] is too short. \n"+
				"Try using more descriptive names in your code. ");

			}
		}	
		
		// CHECK CONSTANT NAMES
		names.clear();
		names = parser.getConstants();
		for (String constant: names) {
			if (constant.equals("i")) {
				continue;
			}
			if (!(constant.matches(constantFormat))) {
				errorCodeCollector.addErrorCode("ConstantVariableFormat", constant, "");
				errorCodeCollector.addErrorMessage("Naming convention error: " +
				"\nThe local variable with name [" + constant
                + "]\n is a constant and should follow the proper naming pattern. \n"
		        + "For example, constants should look like this: TAX_REDUCTION = 400.");
	
			}
		}
		
		
		// CHECK CLASS NAME 
		List<String> classExpression = parser.getClassExp();
		for(int i=0; i <classExpression.size(); i += 2) {
			if (classExpression.get(i).matches(PascalCasePattern)) {
				
				// check with dictionary only the last word of the class name
				// we have checked that the class name follows the pascal format
				String[] splitClassName = classExpression.get(0).split("(?=\\p{Lu})");
				String keyword = splitClassName[splitClassName.length-1];
				if (dictionary.identifyWord(keyword) != 'n') {
					errorCodeCollector.addErrorCode("ClassNameNotNoun",
							classExpression.get(0), classExpression.get(1));
					
					errorCodeCollector.addErrorMessage("Naming convention error at line: "
		            + classExpression.get(1)
					+ "\nThe class name [" + classExpression.get(0) 
					+ "] is not a noun. Class names should be nouns. "
					+ "\nFor example: ClassNameChecker");
				}
			}else {
			
				errorCodeCollector.addErrorCode("ClassNameCasePattern",
						classExpression.get(0), classExpression.get(1));
				
				errorCodeCollector.addErrorMessage("Naming convention error at "
	            + classExpression.get(1) 
				+ "\nThe class with name [" + classExpression.get(0) + "]\n should "
				+ "follow a Pascal Case Pattern. For example, TaxFilingApplication.");
			}
		}
	
		
		//  CHECK METHOD NAMES
		names.clear();
		names = parser.getMethodNames();
		for (int i=0; i < names.size(); i += 2) {
			String methodName = names.get(i).toString();

			if (methodName.equals("main")){ 
				continue;
			}

			if (methodName.matches(methodCaseFormat)) {

				// check methods are verbs
				String[] splitMethodName = methodName.split("(?=\\p{Lu})");
				String keywordMethod = splitMethodName[0];
				if (!(this.dictionary.identifyWord(keywordMethod) == 'v')) {
					errorCodeCollector.addErrorCode("MethodNameNotAVerb", 
							methodName, names.get(i+1));
					
					errorCodeCollector.addErrorMessage("Naming convention error at line: " 
		            + names.get(i+1)
					+"\nThe method name [" + methodName
					+ "] should be a verb.");
				}
			}else {

				errorCodeCollector.addErrorCode("MethodNameCaseFormat", 
							names.get(i), names.get(i+1));	
				
				errorCodeCollector.addErrorMessage("Naming convention error at line: "
	            + names.get(i+1) 
				+ "\nThe method with name [" + names.get(i) + "] \nshould "
				+ "follow a camel Case Pattern. For example, applyMathFormula().");
	
			}
			
		}
		
		
		/* CHECK THE METHOD PARAMETER'S NAME */		
		names.clear();
		names = parser.getParameters();
		for (int i=0; i < names.size()-1; i += 2) {
			String parameterName = names.get(i).toString();

			if (parameterName.length() <= 3 ) {		
				errorCodeCollector.addErrorCode("MethodParameterNameTooShort", 
				parameterName, names.get(i+1));
		
		        errorCodeCollector.addErrorMessage("Naming convention error at line: "
	            + names.get(i+1) 
				+ "\nThe parameter with name [" + parameterName
				+ "] is too short. \nTry using more descriptive names in your code. ");
			
			
			}else if (!(parameterName.matches(methodParameterCaseFormat))) {	
				
				errorCodeCollector.addErrorCode("MethodParameterNameCaseFormat",
					parameterName, names.get(i+1));
								
				errorCodeCollector.addErrorMessage("Naming convention error at line: "
	            +  names.get(i+1)
				+ "\nThe parameter with name [" + parameterName + "] should "
				+ "follow a camel Case Pattern. \nFor example, parameterOne. ");
	
			}
			
			
		}		
	}

}


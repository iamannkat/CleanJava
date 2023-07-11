package com.CleanJava.demo.CleanJava.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

public class VerboseMessageCreator {
	
//	private HashMap<String,Pair<String, String>> errorCodes;
	private Map<String, Pair<String, String>> errorCodes;
	private List<String> conventionErrorsFinal;
	private ErrorCodeCollector errorCodeCollector;
	
	public VerboseMessageCreator() throws FileNotFoundException{
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
		this.errorCodes = errorCodeCollector.getErrorCodes();
		this.conventionErrorsFinal = new ArrayList<String>();
	}

	public List<String> getVerbose() throws IOException {
	
		for (Map.Entry<String,Pair<String, String>> error : errorCodes.entrySet()) {	

			if (error.getKey().equals("InterfaceNameFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: " 
		           + error.getValue().getSecond() + "\nThe Interface with name with name [" 
				   + error.getValue().getFirst().replace("[" ,"").replace("]", "")
				   + "] should \nfollow a Pascal Case Pattern. For example, TaxFilingApplication. ");
			}
			
			if (error.getKey().equals("PackageCaseFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: " 
			            + error.getValue().getSecond() +
						"\nThe package with name with name [" 
						+ error.getValue().getFirst() + "]\nshould " 
						+ "follow a Pascal Case Pattern. For example, TaxFilingApplication. ");
			}
			
			if (error.getKey().equals("LocalVariableFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: " 
			            + error.getValue().getSecond() +
						"\nThe variable with name [" + error.getValue().getFirst() + "] \nshould "
						+ "follow a camel Case Pattern. For example, taxFilingApplication. ");
			}
			
			if (error.getKey().equals("LocalVariableNameIsTemp")) {
				conventionErrorsFinal.add("Naming convention error at line: "
			            + error.getValue().getSecond()
						+ "\nAvoid using names such as [temp] for your variables. \n"
						+ "Try using more descriptive names in your code. ");
			}
			
			if (error.getKey().equals("LocalVariableIsTooShort")) {
				conventionErrorsFinal.add("Naming convention error at line: "
			             + error.getValue().getSecond() + "\nThe local variable with name [" 
			             + error.getValue().getFirst() + "] is too short. \n"+
						"Try using more descriptive names in your code. ");
			}
			
			if (error.getKey().equals("ConstantVariableFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: " +
						"\nThe local variable with name [" + error.getValue().getFirst()
                        + "]\n is a constant and should follow the proper naming pattern. \n"
				        + "For example, constants should look like this: TAX_REDUCTION = 400.");
			}
			
			if (error.getKey().equals("ClassNameCasePattern")) {
				conventionErrorsFinal.add("Naming convention error at "
			            + error.getValue().getSecond() 
						+ "\nThe class with name [" + error.getValue().getFirst() + "]\n should "
						+ "follow a Pascal Case Pattern. For example, TaxFilingApplication.");
			}
			
			if (error.getKey().equals("ClassNameNotNoun")) {
				conventionErrorsFinal.add( "Naming convention error at line: "
			            + error.getValue().getSecond()
						+ "\nThe class name [" + error.getValue().getFirst() 
						+ "] is not a noun. Class names should be nouns. "
						+ "\nFor example: ClassNameChecker");
			}
			
			if (error.getKey().equals("MethodNameCaseFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: "
			            + error.getValue().getSecond() 
						+ "\nThe method with name [" + error.getValue().getFirst() + "] \nshould "
						+ "follow a camel Case Pattern. For example, applyMathFormula().");
			}
			
			if (error.getKey().equals("MethodNameNotAVerb")) {
				conventionErrorsFinal.add("Naming convention error at line: " 
			            + error.getValue().getSecond() 
						+"\nThe method name [" + error.getValue().getFirst()
						+ "] should be a verb.");
			}
			
			if (error.getKey().equals("MethodParameterNameCaseFormat")) {
				conventionErrorsFinal.add("Naming convention error at line: "
			            + error.getValue().getSecond()
						+ "\nThe parameter with name [" + error.getValue().getFirst() + "] should "
						+ "follow a camel Case Pattern. \nFor example, parameterOne. ");
			}
			
			if (error.getKey().equals("MethodParameterNameTooShort")) {
				conventionErrorsFinal.add("Naming convention error at line: "
			            + error.getValue().getSecond() 
						+ "\nThe parameter with name [" + error.getValue().getFirst() 
						+ "] is too short. \nTry using more descriptive names in your code. ");
			}
			
			/////////////////////////////////////////////////////////////////
			if (error.getKey().equals("TooManyMethodDeclarationsInAClass")) {
				conventionErrorsFinal.add("Convention error: There are too many methods"
				  		+ " in the source. The number of methods is " 
						+ error.getValue().getFirst() + ".\n There should be"
		  				+ " no more than " + error.getValue().getSecond() + " methods. ");
			}
			
			if (error.getKey().equals("TooManyForLoopsInAClass")) {
				conventionErrorsFinal.add("Convention error: There are too "
						+ "many [for] statements in the source"
		  		        + " The number of [for] statements is " 
						+ error.getValue().getFirst() + ".\n There should be"
		  				+ " no more than " + error.getValue().getSecond() + ".");
			}
			
			if (error.getKey().equals("TooManyFieldDeclarationsInAClass")) {
				conventionErrorsFinal.add("Convention error: There are too many "
						+ "fields in the source. The number of fields is " 
						+ error.getValue().getFirst() + ".\n There should be"
		  				+ " no more than " + error.getValue().getSecond() + ". ");
			}
			
			if (error.getKey().equals("MoreThanOneClassDeclarationInAFile")) {
				conventionErrorsFinal.add("Convention error: " 
						+ "\nYour file contains " + error.getValue().getFirst() + " class declarations."
						+ "A java file should only contain one \nclass declaration per java file. ");
			     
			}
			
			if (error.getKey().equals("TooLengthyConstructor")) {
				conventionErrorsFinal.add("Convention Error at line: "
			            + error.getValue().getSecond() 
			            + "\nYour constructor is too lengthy at"
						+ error.getValue().getFirst() + " lines. "); 
			}
			
			if (error.getKey().equals("TooManyCharactersInOneLine")) {
				conventionErrorsFinal.add("Convention error at line: " 
			            + error.getValue().getSecond()
				        + "\nThis line is too long at "+ error.getValue().getFirst() 
				        + " characters. "); 
			}
			
			if (error.getKey().equals("TooManyParamatersInMethod")) {
				conventionErrorsFinal.add("Convention Error: " 
			            + ".\nMethod with name [" + error.getValue().getFirst()  
			            + " parameters\nwhich exceeds the input limit of "
			            + error.getValue().getSecond() + " parameters."); 
			}
			
			if (error.getKey().equals("TooLengthyMethod")) {
				conventionErrorsFinal.add("Convention Error at line: "
			            + error.getValue().getSecond() 
			            + "\nThe method with name [" + error.getValue().getFirst()); 
			}
			
			if (error.getKey().equals("TooLengthyFile")) {
				conventionErrorsFinal.add("Convention Error: The number of lines of your "
						+ "source file is " + error.getValue().getFirst() 
						+ ".\n The limit is " + error.getValue().getSecond() 
						+ ". consider breaking up your class into two seperate classes."); 
			}
			
			if (error.getKey().equals("TooManyVariableDeclarationsPerLine")) {
				conventionErrorsFinal.add("Convention Error at line: " 
			            + error.getValue().getSecond() 
						+ "\nThere are " + error.getValue().getFirst()
						+ " variable declarations"
						+ " on this line. \n"
						+ "There should be obly one variable declaration per line. "); 
			}
			
			if (error.getKey().equals("PrimitiveObsession")) {
				conventionErrorsFinal.add("Convention Error: " 
						+ "\nYour class has too many field \n"
						+ "declarations of primitive types."
						+ "It is usually recommended that you create \n"
						+ "a separate class and group some of them\n"
						+ "into their own class.");
			}
		}
//		System.out.println(conventionErrorsFinal.toString());
		return conventionErrorsFinal;
	}
}

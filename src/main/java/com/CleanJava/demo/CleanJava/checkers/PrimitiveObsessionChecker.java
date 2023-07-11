package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;
import com.github.javaparser.ast.Node;

public class PrimitiveObsessionChecker implements IConventionChecker {
	
	private final List<String> primitives = Arrays.asList("int", "byte", 
			"short", "long", "float", "double", "boolean", "char");
	
	private ParserFacade parser;
	private ErrorCodeCollector errorCodeCollector;
	
	public PrimitiveObsessionChecker() throws FileNotFoundException{
		this.parser = ParserFacade.getInstance();
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}

	@Override
	public void check() throws IOException {
		
		int limit = 0;
		List<Node> fieldNodes = parser.getFieldTypes();
		
		for (Node fieldNode: fieldNodes) {
			
			if (primitives.contains(fieldNode.toString())) {
				limit ++;

				if (limit > 5) {
					
					this.errorCodeCollector.addErrorCode("PrimitiveObsession", "", "");
					
					errorCodeCollector.addErrorMessage("Convention Error: " 
					+ "\nYour class has too many field \n"
					+ "declarations of primitive types."
					+ "It is usually recommended that you create \n"
					+ "a separate class and group some of them\n"
					+ "into their own class.");
				}
			}
		}

//		for (String fieldType: fieldTypes){
//			if (primitives.contains(fieldType)) {
//				limit ++;
//				if (limit > 5) {
//					System.out.println("fieldType: " + fieldType);
//					this.errorCodeCollector.addErrorCode("PrimitiveObsession", "", "");
//					
//					errorCodeCollector.addErrorMessage("Convention Error: " 
//					+ "\nYour class has too many field \n"
//					+ "declarations of primitive types."
//					+ "It is usually recommended that you create \n"
//					+ "a separate class and group some of them\n"
//					+ "into their own class.");
//				}
//			}
//		}

	}
}
 
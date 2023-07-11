package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.CleanJava.demo.CleanJava.helpers.DefaultValueReader;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class DeclarationsPerLineChecker implements IConventionChecker{
	
		private ParserFacade parser;
		private HashMap<String, Integer> hashMap;
		private DefaultValueReader defaultvalue;
		private ErrorCodeCollector errorCodeCollector;
			
		public DeclarationsPerLineChecker() throws FileNotFoundException{
			this.parser = ParserFacade.getInstance();
			this.hashMap = new HashMap<String, Integer>();
		    this.defaultvalue = DefaultValueReader.getInstance();
			this.errorCodeCollector = ErrorCodeCollector.getInstance();
		}

		@Override
		public void check() throws IOException {
			
			List<String> variables = parser.getVariablesNames();
			for (int i=1; i<variables.size(); i+=2) {
				String variable = variables.get(i);
				if (hashMap.containsKey(variable)) {
					hashMap.put(variable, hashMap.get(variable) + 1);

				}else {
	                hashMap.put(variable, 1);
				}
			}
			
			 for (Map.Entry<String, Integer> set :
	             hashMap.entrySet()) {
				 if (set.getValue() > defaultvalue.DECLARATIONS_PER_LINE) {
						errorCodeCollector.addErrorCode("TooManyVariableDeclarationsPerLine",
								Integer.toString(set.getValue()), set.getKey());
						
						errorCodeCollector.addErrorMessage("Convention Error at line: " 
			            + set.getKey()
						+ "\nThere are " + Integer.toString(set.getValue())
						+ " variable declarations on this line. \n"
						+ "There should be only one variable declaration per line. ");
				 }
			 }

		}

}

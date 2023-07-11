package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class ConstructorLengthChecker implements IConventionChecker {
	
	private int max_constructor_length;
	private ParserFacade parser;
	private ErrorCodeCollector errorCodeCollector;

	public ConstructorLengthChecker(int max_constructor_length) throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();
		this.max_constructor_length = max_constructor_length;
		this.errorCodeCollector = ErrorCodeCollector.getInstance();

	}

	@Override
	public void check() throws IOException {
		
		List<String> constructorsInfo = parser.getConstructorLength();

		if (constructorsInfo.size() == 0) {
			return;
		}
		
	    for (int i=0; i < constructorsInfo.size(); i += 3) {
	    	
		    Stream<String> lines = constructorsInfo.get(i).lines(); 

			int lineCount = (int) lines.count() - 2;

			if (lineCount > max_constructor_length) {

				errorCodeCollector.addErrorCode("TooLengthyConstructor",
						Integer.toString(lineCount),
						constructorsInfo.get(2));
				
				errorCodeCollector.addErrorMessage("Convention Error at line: "
	            + constructorsInfo.get(2) 
	            + "\nYour constructor is too lengthy at"
				+ Integer.toString(lineCount) + " lines. "); 
			}
	    }		          	
	}

}

package com.CleanJava.demo.CleanJava.checkers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class ParameterNumberChecker implements IConventionChecker{

	private ParserFacade parser;
	private int max_parameter_number;
	private ErrorCodeCollector errorCodeCollector;
	
	public ParameterNumberChecker(int max_parameter_number) throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();
	    this.max_parameter_number = max_parameter_number;
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
	}

	@Override
	public void check() throws IOException {
				
		List<Object> ParametersList = parser.getMethodParametersNumber();		
		
		for (int i=0; i < ParametersList.size()-1; i += 3) {
			
			String methodName = ParametersList.get(i).toString();
			int parameterNumber = (int)ParametersList.get(i+1);
		
			if (parameterNumber > max_parameter_number) {
				errorCodeCollector.addErrorCode("TooManyParamatersInMethod",
						methodName + "] has " + Integer.toString(parameterNumber),
						Integer.toString(max_parameter_number));
				
				errorCodeCollector.addErrorMessage("Convention Error " 
			            + "\nMethod with name [" + methodName 
			            + "] has " + Integer.toString(parameterNumber)
			            + " parameters\nwhich exceeds the input limit of "
			            + Integer.toString(max_parameter_number) + " parameters."); 
			}
		}	

	}

}

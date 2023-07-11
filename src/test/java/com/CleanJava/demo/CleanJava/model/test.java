package com.CleanJava.demo.CleanJava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.CleanJava.demo.CleanJava.checkers.IConventionChecker;
import com.CleanJava.demo.CleanJava.metrics.CyclomaticComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.LCOMMetric;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

@ExtendWith(MockitoExtension.class)
public class test  implements IConventionChecker{
	
    private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
	  		+ "someExample.java";
   
	   private ParserFacade parser;


		  @BeforeEach
		  void initUseCase() throws FileNotFoundException {
			  parser = new ParserFacade(SRC_PATH); 

		  }
	
	
	  @Test
	  @DisplayName("Ensure the correct calculation of the Cyclomatic Complexity metric.")
	  void testCyclomaticComplexity() throws IOException {
  		
		 System.out.println(parser.getFields());
		 LCOMMetric lo = new LCOMMetric();
	     assertEquals("10",  lo.calculateMetric());

	    
	  }


	@Override
	public void check() throws IOException {
		// TODO Auto-generated method stub
		
	}	
}

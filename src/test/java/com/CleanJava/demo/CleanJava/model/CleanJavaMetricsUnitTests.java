package com.CleanJava.demo.CleanJava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.CleanJava.demo.CleanJava.metrics.BooleanExpressionComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.ClassComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.CyclomaticComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.LCOMMetric;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

@ExtendWith(MockitoExtension.class)
public class CleanJavaMetricsUnitTests {
	
	      private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
		  		+ "CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava"
		  		+ "\\UnitBackEndTests\\createSomething.java";
	
		  private LCOMMetric lcomMetric;
		  private BooleanExpressionComplexityMetric booleanMetric;
		  private ClassComplexityMetric classComplexityChecker;
		  private CyclomaticComplexityMetric cyclomaticComplexity;
	      private ParserFacade parser;


		  @BeforeEach
		  void initUseCase() throws FileNotFoundException {
			  parser = new ParserFacade(SRC_PATH); 

		  }
		  
		  @Test
		  @DisplayName("Ensure the correct calculation of the Cyclomatic Complexity metric.")
		  void testCyclomaticComplexity() throws IOException {
	    		
			 cyclomaticComplexity = new CyclomaticComplexityMetric();
		     String actualMetric = cyclomaticComplexity.calculateMetric();
		     assertEquals("15", actualMetric);
		    
		  }	
		  
		  
		  @Test
		  @DisplayName("Ensure the correct calculation of the Class Complexity metric.")
		  void testClassComplexityChecker() throws IOException {
			  
			  classComplexityChecker = new ClassComplexityMetric();
		      String actualMetric = classComplexityChecker.calculateMetric();
		      assertEquals("5", actualMetric);
		  }	
		  
		  
		  @Test
		  @DisplayName("Ensure the correct calculation of the Boolean Expression Complexity metric.")
		  void testBooleanExpressionComplexityMetric() throws IOException {
			  
			  booleanMetric = new BooleanExpressionComplexityMetric();
		      String actualMetric = booleanMetric.calculateMetric();
		      assertEquals("3", actualMetric);
		  }	
		  
		  @Test
		  @DisplayName("Ensure the correct calculation of the LCOM metric.")
		  void checkLCOMChecker() throws IOException {
			  lcomMetric = new LCOMMetric();
		      String actualMetric = lcomMetric.calculateMetric();
		      assertEquals("0.9989858012170385", actualMetric);
		  }	
		   
}

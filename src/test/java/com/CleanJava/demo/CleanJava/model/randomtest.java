package com.CleanJava.demo.CleanJava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.sound.sampled.Line;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import com.CleanJava.demo.CleanJava.checkers.NamingCheker;
import com.CleanJava.demo.CleanJava.checkers.PrimitiveObsessionChecker;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.metrics.BooleanExpressionComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.ClassComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.CyclomaticComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.LCOMMetric;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;
import com.CleanJava.demo.CleanJava.parser.Parser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.resolution.types.ResolvedType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
@ExtendWith(MockitoExtension.class)
public class randomtest {

		
//		      private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
//			  		+ "CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava"
//			  		+ "\\modeltests\\SomeExample.java";
			  private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
				  		+ "CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava"
				  		+ "\\model\\SomeExample.java";
		
			  private LCOMMetric lcomMetric;
			  private BooleanExpressionComplexityMetric booleanMetric;
			  private ClassComplexityMetric classComplexityChecker;
			  private CyclomaticComplexityMetric cyclomaticComplexity;
		      private ParserFacade parser;
		      private PrimitiveObsessionChecker primitiveObsessionChecker;
		  	  private final String PascalCasePattern = "^[A-Z][a-z]*([A-Z][a-z]*)";

//			  private Map<String,Pair<String, String>> errorCodes = new HashMap<String,Pair<String, String>>();
		  	  private ArrayList<String> errorCodes = new ArrayList<String>();
		      private ErrorCodeCollector errorCodeCollector;	  
			  private NamingCheker namingCheker;
			  private Parser parserTest;

		 	 	
			  @BeforeEach
			  void initUseCase() throws IOException {

//				  parser = new ParserFacade(SRC_PATH); 
//				  errorCodeCollector = ErrorCodeCollector.getInstance();
				  parserTest = new Parser(SRC_PATH); 

			  } 
			  
			  @Test
			  @DisplayName("pascal case test")
			  void testprimitives() throws IOException {
				  
					
//					 File inputCode = new File(SRC_PATH);
//					 CompilationUnit cu = StaticJavaParser.parse(inputCode);
//			      
//
//			        // Find all the calculations with two sides:
//			        cu.findAll(BinaryExpr.class).forEach(be -> {
//			            // Find out what type it has:
//			            ResolvedType resolvedType = be.calculateResolvedType();
//
//			            // Show that it's "double" in every case:
//			            System.out.println(be.toString() + " is a: " + resolvedType);
//			        });
				  parserTest.getBooleanExpCollector();
				  parserTest.getFieldsTypes();
				  parserTest.getClassExp();
				  parserTest.getConstants();
				  parserTest.getCyclomaticStatements();
			        
			        boolean ha = true;
			        assertTrue(ha);

			  }	
			  
//			  @Test
//			  @DisplayName("Ensure the correct calculation of the Cyclomatic Complexity metric.")
//			  void testCyclomaticComplexity() throws IOException {
//		    		
//				 cyclomaticComplexity = new CyclomaticComplexityMetric();
//			     String actualMetric = cyclomaticComplexity.calculateMetric();
//			     assertEquals("15", actualMetric);
//			    
//			  }	
			  
//			  @Test
//			  @DisplayName("testMethodNameCaseFormat")
//			  void testMethodNameCaseFormat() throws IOException {
////				  String actualResult = errorCodes.get("MethodNameCaseFormat").getFirst();
//				  this.namingCheker = new NamingCheker();
//				  this.namingCheker.check();
//				  errorCodes = errorCodeCollector.getErrorMessages();
//				  System.out.println(errorCodes.toString());
////				  assertEquals("createMethodOne", actualResult);
//				  
//			  }
//			  
			  @Test
			  @DisplayName("testMethodNameNotAVerb")
			  void testMethodNameNotAVerb() throws IOException {

				  booleanMetric = new BooleanExpressionComplexityMetric();
				  booleanMetric.calculateMetric();
				  
			  }
			  
			  
//			  @Test
//			  @DisplayName("Ensure the correct calculation of the LCOM metric.")
//			  void checkLCOMChecker() throws IOException {
//				  lcomMetric = new LCOMMetric();
//			      String actualMetric = lcomMetric.calculateMetric();
//			      assertEquals("0.9989858012170385", actualMetric);
//			  }	
}

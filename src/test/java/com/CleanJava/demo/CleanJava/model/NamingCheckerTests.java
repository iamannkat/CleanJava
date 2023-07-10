package com.CleanJava.demo.CleanJava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import com.CleanJava.demo.CleanJava.checkers.NamingCheker;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

@ExtendWith(MockitoExtension.class)
public class NamingCheckerTests {
	
	  private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
		  		+ "CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava"
		  		+ "\\UnitBackEndTests\\create.java";
		  
	  private HashMap<String,Pair<String, String>> errorCodes = new HashMap<String,Pair<String, String>>();
	 
	  private ErrorCodeCollector errorCodeCollector;	  
      private ParserFacade parser ;
	  private NamingCheker namingCheker;
 	 	
	  @BeforeEach
	  void initUseCase() throws IOException {

		  parser = new ParserFacade(SRC_PATH); 
		  errorCodeCollector = ErrorCodeCollector.getInstance();
		  errorCodes = errorCodeCollector.getErrorCodes();

	  }  

	  @Test
	  @DisplayName("testPackageCaseFormat")
	  void testPackageCaseFormat() throws IOException {

		  String linesPerFile = errorCodes.get("PackageCaseFormat").getFirst();
		  assertEquals("com.CleanJava.demo.CleanJava.UnitBackEndTests", linesPerFile);
	  }
	  
	  @Test
	  @DisplayName("testInterfaceNameFormat")
	  void testInterfaceNameFormat() throws IOException {
		  String actualResult = errorCodes.get("InterfaceNameFormat").getFirst();
		  assertEquals("violationinterface", actualResult);
	  }
	  
	  @Test
	  @DisplayName("testLocalVariableFormat")
	  void testLocalVariableFormat() throws IOException {
		  
		  String actualResult = errorCodes.get("LocalVariableFormat").getFirst();
		  assertEquals("Fieldwiththewrongname", actualResult);
	  }
	  
	  @Test
	  @DisplayName("testLocalVariableNameIsTemp")
	  void testLocalVariableNameIsTemp() throws IOException {
		  
		  String actualResult = errorCodes.get("LocalVariableNameIsTemp").getFirst();
		  assertEquals("temp", actualResult);
		  
	  }
	  
	  @Test
	  @DisplayName("testLocalVariableIsTooShort")
	  void testLocalVariableIsTooShort() throws IOException {
		  
		  String actualResult = errorCodes.get("LocalVariableIsTooShort").getFirst();
		  assertEquals("c", actualResult);
		  
	  }
	  
	  @Test
	  @DisplayName("testConstantVariableFormat")
	  void testConstantVariableFormat() throws IOException {
		  
		  String linesPerFile = errorCodes.get("ConstantVariableFormat").getFirst();
		  assertEquals("temp", linesPerFile);
	  }
	  
	  
	  @Test
	  @DisplayName("testClassNameCasePattern")
	  void testClassNameCasePattern() throws IOException {
		  
		  String actualResult = errorCodes.get("ClassNameCasePattern").getFirst();
		  assertEquals("create", actualResult);
		  
	  }
	  
	  @Test
	  @DisplayName("testClassNameNotNoun")
	  void testClassNameNotNoun() throws IOException {
		  String linesPerFile = errorCodes.get("ClassNameNotNoun").getFirst();
		  assertEquals("create", linesPerFile);

	  }
	  
	  @Test
	  @DisplayName("testMethodNameCaseFormat")
	  void testMethodNameCaseFormat() throws IOException {
		  this.namingCheker = new NamingCheker();
		  this.namingCheker.check();	
		  String actualResult = errorCodes.get("MethodNameCaseFormat").getFirst();
		  assertEquals("Aviolationmethod", actualResult);
		  
	  }
	  
	  @Test
	  @DisplayName("testMethodNameNotAVerb")
	  void testMethodNameNotAVerb() throws IOException {
		  
		  String actualResult = errorCodes.get("MethodNameNotAVerb").getFirst();
		  assertEquals("violationmethod", actualResult);
		  
	  }
	  
	  @Test
	  @DisplayName("testMethodParameterNameCaseFormat")
	  void testMethodParameterNameCaseFormat() throws IOException {
		  
		  
		  String linesPerFile = errorCodes.get("MethodParameterNameCaseFormat").getFirst();
		  assertEquals("x", linesPerFile);
	  }
	  
	  @Test
	  @DisplayName("testMethodParameterNameTooShort")
	  void testMethodParameterNameTooShort() throws IOException {
		  
		  String linesPerFile = errorCodes.get("MethodParameterNameTooShort").getFirst();
		  assertEquals("x", linesPerFile);	
		  
	  }

}
